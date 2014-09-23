package com.mh.blog.service.impl;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mh.blog.entity.Message;

@Service("messageService")
public class MessageServiceImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getTotalMessageCount() {
		String sql = "select count(*) from message";
		return this.jdbcTemplate.queryForInt(sql);
	}

	public List<Message> getPageMessage(int curPage, int perPage, String orderBy) {
		int start = (curPage - 1) * perPage;
		String sql = "select * from message " + orderBy + " limit " + start + "," + perPage;
		List<Message> lsMsg = this.jdbcTemplate.query(sql, new Message());
		return lsMsg;
	}

	public boolean canPostMessage(String userip) {
		boolean canPost = true;
		String sql = "select max(post_date) from message where user_ip=?";
		Object[] args = new Object[] { userip };
		int[] argTypes = new int[] { Types.VARCHAR };
		List<Date> lsDate = this.jdbcTemplate.queryForList(sql, args, argTypes, Date.class);
		if (lsDate != null && !lsDate.isEmpty()) {
			Date date = lsDate.get(0);
			if (date != null) {
				Date curDate = new Date();
				if ((curDate.getTime() - date.getTime()) < 5 * 60 * 1000) {
					canPost = false;
				}
			}
		}
		return canPost;
	}

	public Message queryMessageById(int id) {
		String sql = "select * from message where id=?";
		Object[] args = new Object[] { id };
		int[] argTypes = new int[] { Types.INTEGER };
		Message msg = this.jdbcTemplate.queryForObject(sql, args, argTypes, new Message());
		return msg;
	}

	public void updateMessageById(int id, String replyContent) {
		String sql = "update message set reply_content=?,reply_date=? where id=?";
		Object[] args = new Object[] { replyContent, new java.sql.Date(new Date().getTime()), id };
		int[] argTypes = new int[] { Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	public void deleteMessageById(int id) {
		String sql = "delete from message where id=?";
		Object[] args = new Object[] { id };
		int[] argTypes = new int[] { Types.INTEGER };
		this.jdbcTemplate.update(sql, args, argTypes);
	}
}
