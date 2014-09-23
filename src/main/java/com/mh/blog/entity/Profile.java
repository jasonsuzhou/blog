package com.mh.blog.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Profile implements RowMapper<Profile> {

	private int id;
	private String username;
	private String realname;
	private String birthday;
	private String party;
	private String currentLocation;
	private String career;
	private String book;
	private String hoppy;
	private String qq;
	private String qqGroup;
	private String mailTo;
	private String imgUrl;
	private String aboutMe;
	private String aboutBlog;
	private String copyRight;
	private int totalAccess;
	private String years;

	@Override
	public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
		Profile profile = new Profile();
		profile.setId(rs.getInt("id"));
		profile.setUsername(rs.getString("username"));
		profile.setRealname(rs.getString("realname"));
		profile.setBirthday(rs.getString("birthday"));
		profile.setParty(rs.getString("party"));
		profile.setCurrentLocation(rs.getString("current_location"));
		profile.setCareer(rs.getString("career"));
		profile.setBook(rs.getString("book"));
		profile.setHoppy(rs.getString("hoppy"));
		profile.setQq(rs.getString("qq"));
		profile.setQqGroup(rs.getString("qq_group"));
		profile.setMailTo(rs.getString("mail_to"));
		profile.setImgUrl(rs.getString("img_url"));
		profile.setAboutMe(rs.getString("about_me"));
		profile.setAboutBlog(rs.getString("about_blog"));
		profile.setCopyRight(rs.getString("copy_right"));
		profile.setTotalAccess(rs.getInt("total_access"));
		profile.setYears(rs.getString("years"));
		return profile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getHoppy() {
		return hoppy;
	}

	public void setHoppy(String hoppy) {
		this.hoppy = hoppy;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getQqGroup() {
		return qqGroup;
	}

	public void setQqGroup(String qqGroup) {
		this.qqGroup = qqGroup;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getAboutBlog() {
		return aboutBlog;
	}

	public void setAboutBlog(String aboutBlog) {
		this.aboutBlog = aboutBlog;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	public int getTotalAccess() {
		return totalAccess;
	}

	public void setTotalAccess(int totalAccess) {
		this.totalAccess = totalAccess;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	
}
