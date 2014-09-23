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

import com.mh.blog.entity.Message;
import com.mh.blog.service.impl.MessageServiceImpl;
import com.mh.blog.util.CommonUtil;

@Controller
public class MessageController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MessageServiceImpl messageService;

	@RequestMapping("addMessage.do")
	public @ResponseBody
	Object addMomentsCommit(HttpServletRequest request) {
		String user = CommonUtil.getClientIpAddr(request);
		boolean canPost = this.messageService.canPostMessage(user);
		Map<String, Object> map = new HashMap<String, Object>();
		if (canPost) {
			String content = request.getParameter("content");
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			String sql = "insert into message(user_ip, content, post_date, reply_date) values(?,?,?,?)";
			Object[] args = new Object[] { user, content, date, date };
			int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP };
			this.jdbcTemplate.update(sql, args, argTypes);
			map.put("dataList", messageService.getPageMessage(1, 10, "order by post_date desc"));
			map.put("totalCount", messageService.getTotalMessageCount());
			map.put("currentPage", 1);
		} else {
			map.put("errorMessage", "You have just post the message, try 5 minutes later.");
		}
		return map;
	}

	@RequestMapping("queryMessage.do")
	public @ResponseBody
	Object queryMessage(HttpServletRequest request) {
		String curPage = request.getParameter("curPage");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", messageService.getPageMessage(Integer.parseInt(curPage), 10, "order by post_date desc"));
		map.put("totalCount", messageService.getTotalMessageCount());
		map.put("currentPage", 1);
		return map;
	}

	@RequestMapping("admin/adminQueryMessage.do")
	public @ResponseBody
	Object adminQueryMessage(HttpServletRequest request) {
		String curPage = request.getParameter("curPage");
		if (StringUtils.isBlank(curPage)) {
			curPage = "1";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", messageService.getPageMessage(Integer.parseInt(curPage), 10, "order by post_date desc"));
		map.put("totalCount", messageService.getTotalMessageCount());
		map.put("currentPage", 1);
		return map;
	}

	@RequestMapping("admin/gotoEditPage.do")
	public ModelAndView gotoEditMessagePage(String id) {
		Message msg = this.messageService.queryMessageById(Integer.parseInt(id));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", msg);
		return new ModelAndView("backend/message/edit", map);
	}

	@RequestMapping("admin/adminUpdateMessage.do")
	public ModelAndView gotoEditMessagePage(String id, String replyContent) {
		this.messageService.updateMessageById(Integer.parseInt(id), replyContent);
		return new ModelAndView(new RedirectView("gotoMessagePage.do"));
	}

	@RequestMapping("admin/adminDeleteMessage.do")
	public ModelAndView adminDeleteMessage(String id) {
		this.messageService.deleteMessageById(Integer.parseInt(id));
		return new ModelAndView(new RedirectView("gotoMessagePage.do"));
	}
}
