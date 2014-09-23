package com.mh.blog.service.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mh.blog.entity.Message;
import com.mh.blog.entity.Moments;

@Service("momentsService")
public class MomentsServiceImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getTotalMomentsCount() {
		String sql = "select count(*) from moments";
		return this.jdbcTemplate.queryForInt(sql);
	}

	public List<Moments> getPageMoments(int curPage, int perPage, String orderBy) {
		int start = (curPage - 1) * perPage;
		String sql = "select * from moments " + orderBy + " limit " + start + "," + perPage;
		List<Moments> lsMsg = this.jdbcTemplate.query(sql, new Moments());
		return lsMsg;
	}

	public Moments queryMomentsById(int id) {
		String sql = "select * from moments where id=?";
		Object[] args = new Object[] { id };
		int[] argTypes = new int[] { Types.INTEGER };
		Moments mom = this.jdbcTemplate.queryForObject(sql, args, argTypes, new Moments());
		return mom;
	}

	public void updateMomentsById(int id, String content) {
		String sql = "update moments set content=? where id=?";
		Object[] args = new Object[] { content, id };
		int[] argTypes = new int[] { Types.VARCHAR, Types.INTEGER };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	public void deleteMomentsById(int id) {
		String sql = "delete from moments where id=?";
		Object[] args = new Object[] { id };
		int[] argTypes = new int[] { Types.INTEGER };
		this.jdbcTemplate.update(sql, args, argTypes);
	}
}
