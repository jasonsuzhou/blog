package com.mh.blog.service.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("countService")
public class CountService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void countTotalAccess(final Integer id) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String sql = "update footer set access_count=access_count+1 where id=?";
				Object[] args = new Object[] { id };
				int[] argTypes = new int[] { Types.INTEGER };
				jdbcTemplate.update(sql, args, argTypes);
			}
		}).start();
	}

	public void updateTotalAccess() {
		new Thread(new Runnable() {
			public void run() {
				String querysql = "select id from footer";
				List<Map<String, Object>> lsContent = jdbcTemplate.queryForList(querysql);
				if (lsContent != null && !lsContent.isEmpty()) {
					countTotalAccess((Integer) lsContent.get(0).get("id"));
				}
			}
		}).start();
	}

}
