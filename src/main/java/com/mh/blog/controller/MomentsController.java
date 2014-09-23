package com.mh.blog.controller;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mh.blog.entity.Moments;
import com.mh.blog.service.impl.MomentsServiceImpl;

@Controller
public class MomentsController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MomentsServiceImpl momentsService;

	@RequestMapping("admin/addMomentsCommit.do")
	public ModelAndView addMomentsCommit(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String content = request.getParameter("content");
		java.sql.Date date = new java.sql.Date(new Date().getTime());
		String sql = "insert into moments(content, post_date) values(?,?)";
		Object[] args = new Object[] { content, date };
		int[] argTypes = new int[] { Types.VARCHAR, Types.TIMESTAMP };
		this.jdbcTemplate.update(sql, args, argTypes);
		return new ModelAndView(new RedirectView("gotoMomentsPage.do"));
	}

	@RequestMapping("queryMoments.do")
	public @ResponseBody
	Object queryMessage(HttpServletRequest request) {
		String curPage = request.getParameter("curPage");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", momentsService.getPageMoments(Integer.parseInt(curPage), 10, "order by post_date desc"));
		map.put("totalCount", momentsService.getTotalMomentsCount());
		map.put("currentPage", 1);
		return map;
	}

	@RequestMapping("admin/adminQueryMoments.do")
	public @ResponseBody
	Object adminQueryMoments(HttpServletRequest request) {
		String curPage = request.getParameter("curPage");
		if (StringUtils.isBlank(curPage)) {
			curPage = "1";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", momentsService.getPageMoments(Integer.parseInt(curPage), 10, "order by post_date desc"));
		map.put("totalCount", momentsService.getTotalMomentsCount());
		map.put("currentPage", 1);
		return map;
	}

	@RequestMapping("admin/gotoEditMomentsPage.do")
	public ModelAndView gotoEditMomentsPage(String id) {
		Moments mom = this.momentsService.queryMomentsById(Integer.parseInt(id));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", mom);
		return new ModelAndView("backend/moments/edit", map);
	}

	@RequestMapping("admin/adminUpdateMoments.do")
	public ModelAndView gotoEditMessagePage(String id, String content) {
		this.momentsService.updateMomentsById(Integer.parseInt(id), content);
		return new ModelAndView(new RedirectView("gotoMomentsPage.do"));
	}

	@RequestMapping("admin/adminDeleteMoments.do")
	public ModelAndView gotoEditMessagePage(String id) {
		this.momentsService.deleteMomentsById(Integer.parseInt(id));
		return new ModelAndView(new RedirectView("gotoMomentsPage.do"));
	}

}
