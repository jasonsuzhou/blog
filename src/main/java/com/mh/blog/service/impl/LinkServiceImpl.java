package com.mh.blog.service.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mh.blog.cache.CacheManager;
import com.mh.blog.entity.Link;
import com.mh.blog.util.CommonUtil;
import com.mh.blog.util.Const;

@Service("linkService")
public class LinkServiceImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Link> queryLinkList() {
		String sql = "select * from link";
		return this.jdbcTemplate.query(sql, new Link());
	}

	public void addLink(Link link) {
		String sql = "insert into link(name,url) values(?,?)";
		Object[] args = new Object[] { link.getName(), link.getUrl() };
		int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR };
		this.jdbcTemplate.update(sql, args, argTypes);
		resetCache();
	}

	public int getTotalCount() {
		String sql = "select count(*) from link";
		return this.jdbcTemplate.queryForInt(sql);
	}

	public List<Link> getPageLinks(int curPage, int perPage) {
		String sql = CommonUtil.getPageQuerySQL(curPage, perPage, Const.tableName.LINK);
		return this.jdbcTemplate.query(sql, new Link());
	}

	public Link queryLinkById(int id) {
		String sql = "select * from link where id=?";
		Object[] args = new Object[] { id };
		int[] argTypes = new int[] { Types.INTEGER };
		return this.jdbcTemplate.queryForObject(sql, args, argTypes, new Link());
	}

	public void updateLink(Link link) {
		String sql = "update link set name=?, url=? where id=?";
		Object[] args = new Object[] { link.getName(), link.getUrl(), link.getId() };
		int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		this.jdbcTemplate.update(sql, args, argTypes);
		resetCache();
	}

	public void deleteLinkById(int id) {
		String sql = "delete from link where id = ?";
		Object[] args = new Object[] { id };
		int[] argTypes = new int[] { Types.INTEGER };
		this.jdbcTemplate.update(sql, args, argTypes);
		resetCache();
	}

	private void resetCache() {
		new Thread(new Runnable() {
			public void run() {
				CacheManager.resetCachedLinks(jdbcTemplate);
			}
		}).start();
	}
}
