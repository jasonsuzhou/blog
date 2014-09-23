package com.mh.blog.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Link implements RowMapper<Link> {

	private int id;
	private String name;
	private String url;

	@Override
	public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
		Link link = new Link();
		link.setId(rs.getInt("id"));
		link.setName(rs.getString("name"));
		link.setUrl(rs.getString("url"));
		return link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
