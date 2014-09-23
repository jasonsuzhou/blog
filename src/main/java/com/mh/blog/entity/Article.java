package com.mh.blog.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.mh.blog.cache.DateUtil;

public class Article implements RowMapper<Article> {
	private int id;
	private String title;
	private String content;
	private String keyWords;
	private String previousArticle;
	private String nextArticle;
	private String relatedArticles;
	private String summaryImg;
	private Date postDate;
	private Date updateDate;
	private String strPostDate;
	private String strUpdateDate;
	private String category;
	private String author;
	private int total;
	private String downloadUrl;

	@Override
	public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setTitle(rs.getString("title"));
		article.setContent(rs.getString("content"));
		article.setKeyWords(rs.getString("key_words"));
		article.setPreviousArticle(rs.getString("previous_url"));
		article.setNextArticle(rs.getString("next_url"));
		article.setRelatedArticles(rs.getString("related_articles"));
		article.setSummaryImg(rs.getString("summary_view_url"));
		article.setPostDate(rs.getTimestamp("post_date"));
		article.setUpdateDate(rs.getTimestamp("update_date"));
		article.setAuthor(rs.getString("author"));
		article.setCategory(rs.getString("category"));
		article.setTotal(rs.getInt("total"));
		article.setDownloadUrl(rs.getString("download_url"));
		return article;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getPreviousArticle() {
		return previousArticle;
	}

	public void setPreviousArticle(String previousArticle) {
		this.previousArticle = previousArticle;
	}

	public String getNextArticle() {
		return nextArticle;
	}

	public void setNextArticle(String nextArticle) {
		this.nextArticle = nextArticle;
	}

	public String getRelatedArticles() {
		return relatedArticles;
	}

	public void setRelatedArticles(String relatedArticles) {
		this.relatedArticles = relatedArticles;
	}

	public String getSummaryImg() {
		return summaryImg;
	}

	public void setSummaryImg(String summaryImg) {
		this.summaryImg = summaryImg;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getStrPostDate() {
		return DateUtil.date2String(this.postDate);
	}

	public void setStrPostDate(String strPostDate) {
		this.strPostDate = strPostDate;
	}

	public String getStrUpdateDate() {
		return DateUtil.date2String(this.updateDate);
	}

	public void setStrUpdateDate(String strUpdateDate) {
		this.strUpdateDate = strUpdateDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
}
