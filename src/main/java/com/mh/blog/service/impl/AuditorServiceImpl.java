package com.mh.blog.service.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mh.blog.entity.Auditor;
import com.mh.blog.util.CommonUtil;
import com.mh.blog.util.Const;

@Service("auditorService")
public class AuditorServiceImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean canLogin(String ip) {
		Auditor auditor = this.queryAuditorByIP(ip);
		if (auditor == null) {
			insertOneLoginRecord(ip);
			return true;
		} else {
			addOneMoreAttempTimes(ip);
			if ("Y".equalsIgnoreCase(auditor.getIsBlack())) {
				return false;
			}
			int attempTimes = auditor.getAttempTimes();
			if (attempTimes > 4) {
				setIPToBlackList(ip);
				return false;
			} else {
				return true;
			}
		}
	}

	public void initLoginStatus(String ip) {
		String sql = "update auditor set attemp_times=0 where ip = ? and type='Login'";
		Object[] args = new Object[] { ip };
		int[] argTypes = new int[] { Types.VARCHAR };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	private void addOneMoreAttempTimes(String ip) {
		String sql = "update auditor set attemp_times=attemp_times+1, total_attemp_times=total_attemp_times+1 where ip=? and type='Login'";
		Object[] args = new Object[] { ip };
		int[] argTypes = new int[] { Types.VARCHAR };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	private void setIPToBlackList(String ip) {
		String sql = "update auditor set is_black=? where ip = ? and type='Login'";
		Object[] args = new Object[] { "Y", ip };
		int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	private Auditor queryAuditorByIP(String ip) {
		String sql = "select * from auditor where ip = ? and type='Login'";
		Object[] args = new Object[] { ip };
		int[] argTypes = new int[] { Types.VARCHAR };
		List<Auditor> list = this.jdbcTemplate.query(sql, args, argTypes, new Auditor());
		if (CommonUtil.isListEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}

	private void insertOneLoginRecord(String ip) {
		String sql = "insert into auditor(type,ip) values(?,?)";
		Object[] args = new Object[] { "Login", ip };
		int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	public List<Auditor> queryAuditor(int curPage, int perPage) {
		String sql = CommonUtil.getPageQuerySQL(curPage, perPage, Const.tableName.AUDITOR);
		return this.jdbcTemplate.query(sql, new Auditor());
	}

	public int getTotalCount() {
		String sql = "select count(*) from auditor";
		return this.jdbcTemplate.queryForInt(sql);
	}

	public void deleteById(int id) {
		String sql = CommonUtil.getDeleteSQL(Const.tableName.AUDITOR);
		Object[] args = new Object[] { id };
		int[] argTypes = new int[] { Types.INTEGER };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	public Auditor queryAuditorById(int id) {
		String sql = CommonUtil.getDetailSQL(Const.tableName.AUDITOR);
		Object[] args = CommonUtil.genSQLObjects(id);
		int[] argTypes = CommonUtil.genSQLTypes(Types.INTEGER);
		return this.jdbcTemplate.queryForObject(sql, args, argTypes, new Auditor());
	}

	public void resetBlackIP(int id, int attempTimes, String isBlack) {
		String[] fields = new String[] { "attemp_times", "is_black" };
		String sql = CommonUtil.getUpdateSQL(Const.tableName.AUDITOR, fields);
		Object[] args = CommonUtil.genSQLObjects(attempTimes, isBlack, id);
		int[] argTypes = CommonUtil.genSQLTypes(Types.INTEGER, Types.VARCHAR, Types.INTEGER);
		this.jdbcTemplate.update(sql, args, argTypes);
	}

}
