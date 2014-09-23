package com.mh.blog.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.mh.blog.cache.DateUtil;

public class Message implements RowMapper<Message> {
	
	private int id;
	private String userip;
	private String content;
	private String replyContent;;
	private Date postDate;
	private Date replyDate;
	private String strPostDate;
	private String strReplyDate;

	@Override
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message msg = new Message();
		msg.setId(rs.getInt("id"));
		msg.setUserip(rs.getString("user_ip"));
		msg.setContent(rs.getString("content"));
		msg.setReplyContent(rs.getString("reply_content"));
		msg.setPostDate(rs.getTimestamp("post_date"));
		msg.setReplyDate(rs.getTimestamp("reply_date"));
		return msg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getStrPostDate() {
		return DateUtil.date2String(this.postDate);
	}

	public void setStrPostDate(String strPostDate) {
		this.strPostDate = strPostDate;
	}

	public String getStrReplyDate() {
		return DateUtil.date2String(this.replyDate);
	}

	public void setStrReplyDate(String strReplyDate) {
		this.strReplyDate = strReplyDate;
	}

}
