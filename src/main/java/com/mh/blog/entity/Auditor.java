package com.mh.blog.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Auditor implements RowMapper<Auditor> {

	private int id;
	private String type;
	private String ip;
	private String isBlack;
	private int attempTimes;
	private int totalAttempTimes;
	private String success;
	private String remark;

	@Override
	public Auditor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Auditor auditor = new Auditor();
		auditor.setId(rs.getInt("id"));
		auditor.setType(rs.getString("type"));
		auditor.setIp(rs.getString("ip"));
		auditor.setIsBlack(rs.getString("is_black"));
		auditor.setAttempTimes(rs.getInt("attemp_times"));
		auditor.setTotalAttempTimes(rs.getInt("total_attemp_times"));
		auditor.setSuccess(rs.getString("success"));
		auditor.setRemark(rs.getString("remark"));
		return auditor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(String isBlack) {
		this.isBlack = isBlack;
	}

	public int getAttempTimes() {
		return attempTimes;
	}

	public void setAttempTimes(int attempTimes) {
		this.attempTimes = attempTimes;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public int getTotalAttempTimes() {
		return totalAttempTimes;
	}

	public void setTotalAttempTimes(int totalAttempTimes) {
		this.totalAttempTimes = totalAttempTimes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
