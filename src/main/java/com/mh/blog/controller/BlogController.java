package com.mh.blog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mh.blog.entity.Article;
import com.mh.blog.entity.Category;
import com.mh.blog.service.impl.BlogServiceImpl;

@Controller
public class BlogController {

	@Autowired
	private BlogServiceImpl blogService;

	@RequestMapping("queryBlogList.do")
	public @ResponseBody
	Object queryBlogList(String curPage, String perPage) {
		// query category
		List<Category> lsCategory = this.blogService.queryAllCategory();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryList", lsCategory);
		// query article
		if (StringUtils.isBlank(curPage)) {
			curPage = "1";
		}
		if (StringUtils.isBlank(perPage)) {
			perPage = "10";
		}
		int totalCount = this.blogService.getTotalArticleCount();
		List<Article> lsArticle = this.blogService.getPageArticleList(Integer.parseInt(curPage),
				Integer.parseInt(perPage));
		map.put("dataList", lsArticle);
		map.put("totalCount", totalCount);
		map.put("currentPage", curPage);
		return map;
	}

	@RequestMapping("gotoBlogDetail.do")
	public ModelAndView gotoBlogDetailPage(String id) {
		int articleid = Integer.parseInt(id);
		Article article = this.blogService.queryArticleById(articleid);
		Article preArticle = this.blogService.findPreviousArticle(articleid);
		Article nextArticle = this.blogService.findNextArticle(articleid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", article);
		if(preArticle != null) {
			map.put("preArticleTitle", preArticle.getTitle());
			map.put("preArticleId", preArticle.getId());
		} else {
			map.put("preArticleTitle", "");
			map.put("preArticleId", "");
		}
		if(nextArticle != null) {
			map.put("nextArticleTitle", nextArticle.getTitle());
			map.put("nextArticleId", nextArticle.getId());
		} else {
			map.put("nextArticleTitle", "");
			map.put("nextArticleId", "");
		}
		map.put("relatedArticles", getRelatedArticles(article.getCategory()));
		return new ModelAndView("front/blogdetail", map);
	}

	private String getRelatedArticles(String category) {
		List<Article> list = this.blogService.findRelatedArticles(category);
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size()>0) {
			for (Article article : list) {
				sb.append("<li><a href=\"");
				sb.append("gotoBlogDetail.do?id=");
				sb.append(article.getId()).append("\" title=\"");
				sb.append(article.getTitle());
				sb.append("\">");
				sb.append(article.getTitle());
				sb.append("</a>");
				sb.append("</li>");
			}
		}
		return sb.toString();
	}

	@RequestMapping("queryLatestArticle.do")
	public @ResponseBody
	Object queryLatestArticle() {
		List<Article> lsArticle = this.blogService.queryLatestArticle();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", lsArticle);
		return map;
	}

	@RequestMapping("queryTopArticle.do")
	public @ResponseBody
	Object queryTopArticle() {
		List<Article> list = this.blogService.queryTopArticle();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", list);
		return map;
	}

	@RequestMapping("admin/queryAllCategory.do")
	public @ResponseBody
	Object queryAllCategory() {
		List<Category> lsCategory = this.blogService.queryAllCategory();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", lsCategory);
		return map;
	}

	@RequestMapping("admin/addArticleCommit.do")
	public ModelAndView adminAddArticleCommit(HttpServletRequest request) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String keyWords = request.getParameter("keyWords");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		String imgURL = request.getParameter("summaryImg");
		String downloadUrl = request.getParameter("downloadUrl");
		Article article = new Article();
		article.setAuthor(author);
		article.setTitle(title);
		article.setCategory(category);
		article.setContent(content);
		article.setKeyWords(keyWords);
		article.setDownloadUrl(downloadUrl);
		Date date = new Date();
		article.setPostDate(date);
		article.setUpdateDate(date);
		article.setSummaryImg(imgURL);
		this.blogService.addNewArticle(article);
		return new ModelAndView(new RedirectView("gotoBlogArticlePage.do"));
	}

	@RequestMapping("admin/adminUpdateArticle.do")
	public ModelAndView adminUpdateBlogArticle(HttpServletRequest request) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String keyWords = request.getParameter("keyWords");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		String imgURL = request.getParameter("summaryImg");
		String downloadUrl = request.getParameter("downloadUrl");
		Article article = new Article();
		article.setId(Integer.parseInt(request.getParameter("id")));
		article.setAuthor(author);
		article.setTitle(title);
		article.setCategory(category);
		article.setContent(content);
		article.setKeyWords(keyWords);
		article.setDownloadUrl(downloadUrl);
		Date date = new Date();
		article.setPostDate(date);
		article.setUpdateDate(date);
		article.setSummaryImg(imgURL);
		this.blogService.updateArticle(article);
		return new ModelAndView(new RedirectView("gotoBlogArticlePage.do"));
	}

	@RequestMapping("admin/adminQueryBlogCategory.do")
	public @ResponseBody
	Object adminQueryCategory(String curPage, String perPage) {
		if (StringUtils.isBlank(curPage)) {
			curPage = "1";
		}
		if (StringUtils.isBlank(perPage)) {
			perPage = "10";
		}
		int currentPage = Integer.parseInt(curPage);
		int iperPage = Integer.parseInt(perPage);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", this.blogService.getTotalCategoryCount());
		map.put("currentPage", currentPage);
		map.put("dataList", this.blogService.getPageCategoryList(currentPage, iperPage));
		return map;
	}

	@RequestMapping("admin/adminQueryArticle.do")
	public @ResponseBody
	Object adminQueryArticle(String curPage, String perPage, String condition) {
		if (StringUtils.isBlank(curPage)) {
			curPage = "1";
		}
		if (StringUtils.isBlank(perPage)) {
			perPage = "10";
		}
		int currentPage = Integer.parseInt(curPage);
		int iperPage = Integer.parseInt(perPage);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", this.blogService.getTotalArticleCount());
		map.put("currentPage", currentPage);
		map.put("dataList", this.blogService.getPageArticleList(currentPage, iperPage));
		return map;
	}

	@RequestMapping("admin/adminAddBlogCategory.do")
	public ModelAndView adminAddCategory(String name) {
		this.blogService.addNewCategory(name);
		return new ModelAndView(new RedirectView("gotoBlogCategoryPage.do"));
	}

	@RequestMapping("admin/adminDeleteBlogCategory.do")
	public ModelAndView adminDeleteBlogCategory(String id) throws Exception {
		this.blogService.deleteCategoryById(Integer.parseInt(id));
		return new ModelAndView(new RedirectView("gotoBlogCategoryPage.do"));
	}

	@RequestMapping("admin/adminDeleteBlogArticle.do")
	public ModelAndView adminDeleteBlogArticle(String id) {
		this.blogService.deleteArticleById(Integer.parseInt(id));
		return new ModelAndView(new RedirectView("gotoBlogArticlePage.do"));
	}

	@RequestMapping("admin/gotoEditBlogCategoryPage.do")
	public ModelAndView gotoEditCategoryPage(String id) {
		Category category = this.blogService.queryCategoryById(Integer.parseInt(id));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", category);
		return new ModelAndView("backend/category/edit", map);
	}

	@RequestMapping("admin/gotoEditBlogArticlePage.do")
	public ModelAndView gotoEditBlogArticlePage(String id, HttpServletRequest request) {
		Article article = this.blogService.queryArticleById(Integer.parseInt(id));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", article);
		String content = article.getContent();
		content = content.replaceAll("&gt;", "\001");
		content = content.replaceAll("&lt;", "\002");
		request.setAttribute("contentValue", content);
		return new ModelAndView("backend/article/edit", map);
	}

	@RequestMapping("admin/adminUpdateBlogCategory.do")
	public ModelAndView gotoEditMessagePage(String id, String name) {
		this.blogService.updateCategoryById(Integer.parseInt(id), name);
		return new ModelAndView(new RedirectView("gotoBlogCategoryPage.do"));
	}

}
