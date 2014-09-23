package com.mh.blog.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.mh.blog.cache.DateUtil;

public class Moments implements RowMapper<Moments> {

	private int id;
	private Date postDate;
	private String strPostDate;
	private String content;

	@Override
	public Moments mapRow(ResultSet rs, int rowNum) throws SQLException {
		Moments mom = new Moments();
		mom.setId(rs.getInt("id"));
		mom.setPostDate(rs.getTimestamp("post_date"));
		mom.setContent(rs.getString("content"));
		return mom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getStrPostDate() {
		return DateUtil.date2String(this.getPostDate());
	}

	public void setStrPostDate(String strPostDate) {
		this.strPostDate = strPostDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
