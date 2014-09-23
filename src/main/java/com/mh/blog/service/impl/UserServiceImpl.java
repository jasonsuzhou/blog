package com.mh.blog.service.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mh.blog.entity.User;
import com.mh.blog.util.CommonUtil;
import com.mh.blog.util.SHA1Util;

@Service("userService")
public class UserServiceImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean successLogin(String username, String password, String verifyCode) {
		boolean canLogin = false;
		User user = this.queryUser(username, verifyCode);
		if (user != null) {
			if (!isUserLocked(user.getLocked())) {
				if (isPasswordSame(password, user.getPassword())) {
					canLogin = true;
					resetInputErrorTimes(username);
				} else {
					addInputErrorTimes(username, user.getInputErrorTimes());
				}
			}
		}
		return canLogin;
	}

	private void addInputErrorTimes(String username, int loginErrorTimes) {
		String sql = "";
		if (loginErrorTimes > 3) {
			sql = "update user set locked='Y' where username=?";
		} else {
			sql = "update user set input_error_times=input_error_times+1 where username=?";
		}
		Object[] args = new Object[] { username };
		int[] argTypes = new int[] { Types.VARCHAR };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	private void resetInputErrorTimes(String username) {
		String sql = "update user set input_error_times=0 where username=?";
		Object[] args = new Object[] { username };
		int[] argTypes = new int[] { Types.VARCHAR };
		this.jdbcTemplate.update(sql, args, argTypes);
	}

	private User queryUser(String username, String verifyCode) {
		String sql = "select * from user where username=? and verify_code=?";
		Object[] args = new Object[] { username, verifyCode };
		int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR };
		List<User> list = this.jdbcTemplate.query(sql, args, argTypes, new User());
		if (CommonUtil.isListNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

	private boolean isUserLocked(String lockFlag) {
		return "Y".equals(lockFlag);
	}

	private boolean isPasswordSame(String inputPass, String oriPass) {
		String inputPassword = SHA1Util.getSummaryPassword(inputPass);
		return oriPass.equals(inputPassword);
	}

	public User queryUserByUsername(String username) {
		String sql = "select * from user where username=?";
		Object[] args = CommonUtil.genSQLObjects(username);
		int[] argTypes = CommonUtil.genSQLTypes(Types.VARCHAR);
		return this.jdbcTemplate.queryForObject(sql, args, argTypes, new User());
	}

	public boolean isOriginalPassCorrect(String oldPass, String password) {
		String sha1Pass = SHA1Util.getSummaryPassword(oldPass);
		return password.equals(sha1Pass);
	}

	public void updatePassword(String username, String newPass) {
		String sha1Pass = SHA1Util.getSummaryPassword(newPass);
		String sql = "update user set password=?";
		Object[] args = CommonUtil.genSQLObjects(sha1Pass);
		int[] argTypes = CommonUtil.genSQLTypes(Types.VARCHAR);
		this.jdbcTemplate.update(sql, args, argTypes);
	}

}
