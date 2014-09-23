package com.mh.blog.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class User implements RowMapper<User> {

	private String username;
	private String password;
	private int inputErrorTimes;
	private Date lastLoginTime;
	private String locked;
	private String verifyCode;

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setInputErrorTimes(rs.getInt("input_error_times"));
		user.setLastLoginTime(rs.getDate("last_login_time"));
		user.setLocked(rs.getString("locked"));
		user.setVerifyCode(rs.getString("verify_code"));
		return user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getInputErrorTimes() {
		return inputErrorTimes;
	}

	public void setInputErrorTimes(int inputErrorTimes) {
		this.inputErrorTimes = inputErrorTimes;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	
}
