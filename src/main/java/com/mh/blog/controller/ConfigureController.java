package com.mh.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConfigureController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

}
