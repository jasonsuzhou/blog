package com.mh.blog.service.impl;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mh.blog.cache.CacheManager;
import com.mh.blog.entity.Profile;

@Service("profileService")
public class ProfileServiceImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Profile queryProfile() {
		String sql = "select * from profile";
		return this.jdbcTemplate.query(sql, new Profile()).get(0);
	}

	public void updateProfile(Profile profile) {
		String sql = "update profile set username=?,realname=?,about_me=?,about_blog=?,party=?,birthday=?, current_location=?," +
				"career=?, book=?, hoppy=?, img_url=?";
		String username = profile.getUsername();
		String realname = profile.getRealname();
		String aboutMe = profile.getAboutMe();
		String aboutBlog = profile.getAboutBlog();
		String party = profile.getParty();
		String birthday = profile.getBirthday();
		String currentLocation = profile.getCurrentLocation();
		String career = profile.getCareer();
		String book = profile.getBook();
		String hobby = profile.getHoppy();
		String imgUrl = profile.getImgUrl();
		Object[] args = new Object[] { username, realname, aboutMe, aboutBlog, party, birthday, currentLocation,
										career, book, hobby, imgUrl};
		int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
									Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		this.jdbcTemplate.update(sql, args, argTypes);
		new Thread(new Runnable() {
			public void run() {
				CacheManager.resetCachedProfile(jdbcTemplate);
			}
		}).start();
	}

}
