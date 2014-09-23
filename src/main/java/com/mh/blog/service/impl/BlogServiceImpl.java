package com.mh.blog.service.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mh.blog.cache.CacheManager;
import com.mh.blog.entity.Article;
import com.mh.blog.entity.Category;
import com.mh.blog.util.CommonUtil;

@Service("blogService")
public class BlogServiceImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getTotalCategoryCount() {
		String sql = "select count(*) from category";
		return this.jdbcTemplate.queryForInt(sql);
	}

	public List<Category> getPageCategoryList(int curPage, int perPage) {
		int start = CommonUtil.getStartRow(curPage, perPage);
		String sql = "select * from category limit " + start + "," + perPage;
		return this.jdbcTemplate.query(sql, new Category());
	}

	public void addNewCategory(String name) {
		String sql = "insert into category(name) values (?)";
		Object[] args = new Object[] { name };
		int[] argTypes = new int[] { Types.VARCHAR };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	public void deleteCategoryById(int id) throws Exception {
		Category cat = this.queryCategoryById(id);
		if (cat.getTotal() > 0) {
			throw new Exception("There are articles under this category, cannot delete.");
		}
		String catname = cat.getName();
		String sql = "select count(*) from article where category=?";
		Object[] args = new Object[] { catname };
		int[] argTypes = new int[] { Types.VARCHAR };
		int result = this.jdbcTemplate.queryForInt(sql, args, argTypes);
		if (result > 0) {
			throw new Exception("There are articles under this category, cannot delete.");
		}
		sql = "delete from category where id=?";
		args = new Object[] { id };
		argTypes = new int[] { Types.INTEGER };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	public Category queryCategoryById(int id) {
		String sql = "select * from category where id=?";
		Object[] args = new Object[] { id };
		int[] argTypes = new int[] { Types.INTEGER };
		return this.jdbcTemplate.queryForObject(sql, args, argTypes, new Category());
	}

	public Article queryArticleById(final int id) {
		String sql = "select * from article where id=?";
		Object[] args = new Object[] { id };
		int[] argTypes = new int[] { Types.INTEGER };
		new Thread(new Runnable() {
			public void run() {
				String sql = "update article set total=total+1 where id=?";
				Object[] args = new Object[] { id };
				int[] argTypes = new int[] { Types.INTEGER };
				jdbcTemplate.update(sql, args, argTypes);
			}
		}).start();
		return this.jdbcTemplate.queryForObject(sql, args, argTypes, new Article());
	}

	public void updateCategoryById(int id, String name) {
		String sql = "update category set name=? where id=?";
		Object[] args = new Object[] { name, id };
		int[] argTypes = new int[] { Types.VARCHAR, Types.INTEGER };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	public List<Category> queryAllCategory() {
		String sql = "select * from category order by total desc";
		return this.jdbcTemplate.query(sql, new Category());
	}

	public int getTotalArticleCount() {
		String sql = "select count(*) from article";
		return this.jdbcTemplate.queryForInt(sql);
	}

	public List<Article> getPageArticleList(int curPage, int perPage) {
		int start = CommonUtil.getStartRow(curPage, perPage);
		String sql = "select * from article order by post_date desc limit " + start + "," + perPage;
		return this.jdbcTemplate.query(sql, new Article());
	}

	public void addNewArticle(Article article) {
		String category = article.getCategory();
		this.updateCategoryCount(category);
		String sql = "insert into article(title,content,key_words,summary_view_url,post_date,update_date,author,category,download_url) values (?,?,?,?,?,?,?,?,?)";
		Object[] args = new Object[] { article.getTitle(), article.getContent(), article.getKeyWords(),
				article.getSummaryImg(), new java.sql.Date(article.getPostDate().getTime()),
				new java.sql.Date(article.getUpdateDate().getTime()), article.getAuthor(), article.getCategory(), article.getDownloadUrl() };
		int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
				Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		this.jdbcTemplate.update(sql, args, argTypes);
		new Thread(new Runnable() {
			public void run() {
				CacheManager.resetCachedLatestArticle(jdbcTemplate);
				CacheManager.resetCachedTopArticle(jdbcTemplate);
				CacheManager.resetCachedWorkDisplay(jdbcTemplate);
			}
		}).start();
	}

	private void updateCategoryCount(String category) {
		if (StringUtils.isNoneBlank(category)) {
			String[] categories = category.split(",");
			String sql = null;
			Object[] args = null;
			int[] argTypes = null;
			for (String cat : categories) {
				if (StringUtils.isNoneBlank(cat)) {
					sql = "update category set total=total+1 where name=?";
					args = new Object[] { cat };
					argTypes = new int[] { Types.VARCHAR };
					this.jdbcTemplate.update(sql, args, argTypes);
				}
			}
		}

	}

	public void updateArticle(Article article) {
		// TODO need calculate the category count
		String sql = "update article set title=?,content=?,key_words=?,summary_view_url=?,update_date=?,author=?,category=?,download_url=? where id=?";
		Object[] args = new Object[] { article.getTitle(), article.getContent(), article.getKeyWords(),
				article.getSummaryImg(), new java.sql.Date(article.getPostDate().getTime()), article.getAuthor(),
				article.getCategory(), article.getDownloadUrl(), article.getId() };
		int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		this.jdbcTemplate.update(sql, args, argTypes);
		new Thread(new Runnable() {
			public void run() {
				CacheManager.resetCachedLatestArticle(jdbcTemplate);
				CacheManager.resetCachedTopArticle(jdbcTemplate);
				CacheManager.resetCachedWorkDisplay(jdbcTemplate);
			}
		}).start();
	}

	public void deleteArticleById(int id) {
		String sql = "delete from article where id=?";
		Object[] args = new Object[] { id };
		int[] argTypes = new int[] { Types.INTEGER };
		final String categoryName = this.getCategoryNameByArticleId(id);
		this.jdbcTemplate.update(sql, args, argTypes);
		new Thread(new Runnable() {
			public void run() {
				updateDownCategoryCount(categoryName);
				CacheManager.resetCachedLatestArticle(jdbcTemplate);
				CacheManager.resetCachedTopArticle(jdbcTemplate);
				CacheManager.resetCachedWorkDisplay(jdbcTemplate);
			}
		}).start();
	}

	public void updateDownCategoryCount(String categoryName) {
		String sql = "update category set total=total-1 where name=?";
		Object[] args = new Object[] { categoryName };
		int[] argTypes = new int[] { Types.VARCHAR };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	public String getCategoryNameByArticleId(int articleId) {
		Article article = this.queryArticleById(articleId);
		return article.getCategory();
	}

	public List<Article> queryLatestArticle() {
		List<Article> lsArticle = CacheManager.getCachedLatestNews(this.jdbcTemplate);
		if (lsArticle == null) {
			lsArticle = new ArrayList<Article>();
		}
		return lsArticle;
	}

	public List<Article> queryTopArticle() {
		List<Article> lsArticle = CacheManager.getCachedTopNews(this.jdbcTemplate);
		if (lsArticle == null) {
			lsArticle = new ArrayList<Article>();
		}
		return lsArticle;
	}

	public Article findPreviousArticle(int curId) {
		String sql = "select * from article where id<=? order by post_date desc limit 0,2";
		Object[] args = new Object[] { curId };
		int[] argTypes = new int[] { Types.INTEGER };
		List<Article> list = this.jdbcTemplate.query(sql, args, argTypes, new Article());
		if (list != null && !list.isEmpty() && list.size() > 1) {
			return list.get(1);
		} else {
			return null;
		}
	}

	public Article findNextArticle(int curId) {
		String sql = "select * from article  where id>=? order by post_date desc limit 0,2";
		Object[] args = new Object[] { curId };
		int[] argTypes = new int[] { Types.INTEGER };
		List<Article> list = this.jdbcTemplate.query(sql, args, argTypes, new Article());
		if (list != null && !list.isEmpty() && list.size() > 1) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<Article> findRelatedArticles(String categoryName) {
		String sql = "select * from article where key_words like ? or title like ? or content like ? limit 0,8";
		String value = "%" + categoryName + "%";
		Object[] args = new Object[] { value, value, value };
		int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		return this.jdbcTemplate.query(sql, args, argTypes, new Article());
	}

}
