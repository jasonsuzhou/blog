package com.mh.blog.controller;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mh.blog.cache.CacheManager;

@Controller
public class FooterConfig {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("getFoorterInfo.do")
	public void getFooterContent(HttpServletResponse response) {
		String footer = CacheManager.getCachedFooterInfo(jdbcTemplate);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().print(footer);
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("admin/adminConfigFooter.do")
	public ModelAndView adminGetFooterContent() {
		Map<String, String> map = new HashMap<String, String>();
		String querysql = "select id, content, access_count from footer";
		List<Map<String, Object>> lsContent = this.jdbcTemplate.queryForList(querysql);
		if (lsContent == null || lsContent.isEmpty()) {
			String insertSQL = "insert into footer(content) values('Not configure yet')";
			this.jdbcTemplate.execute(insertSQL);
			lsContent = this.jdbcTemplate.queryForList(querysql);
		}
		String footer = (String) lsContent.get(0).get("content");
		map.put("footerContent", footer);
		map.put("primaryKey", String.valueOf(lsContent.get(0).get("id")));
		map.put("accessCount", String.valueOf(lsContent.get(0).get("access_count")));
		CacheManager.addCachedFooter(footer);
		return new ModelAndView("backend/config/footer", map);
	}

	@RequestMapping("admin/updateFooterContent.do")
	public ModelAndView updateFooterContent(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		String updatesql = "update footer set content=? where id=?";
		Object[] args = new Object[] { content, Integer.parseInt(id) };
		int[] argTypes = new int[] { Types.VARCHAR, Types.INTEGER };
		this.jdbcTemplate.update(updatesql, args, argTypes);
		return new ModelAndView(new RedirectView("adminConfigFooter.do"));
	}

}
