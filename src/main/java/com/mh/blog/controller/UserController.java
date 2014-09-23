package com.mh.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mh.blog.entity.User;
import com.mh.blog.service.impl.AuditorServiceImpl;
import com.mh.blog.service.impl.UserServiceImpl;
import com.mh.blog.util.CommonUtil;
import com.mh.blog.util.Const;

@Controller
public class UserController {
	@Autowired
	private AuditorServiceImpl auditorService;
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping("/admin.do")
	public String gotoAdminPage(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		String ip = CommonUtil.getClientIpAddr(request);
		if(auditorService.canLogin(ip)) {
			if(userService.successLogin(username, password, verify)) {
				this.auditorService.initLoginStatus(ip);
				request.getSession().invalidate();
				request.getSession(true).setAttribute(Const.Session.USERNAME, username);
				return "backend/index";
			} else {
				throw new RuntimeException("invalid login");
			}
		} else {
			throw new RuntimeException("invalid login");
		}
	}
	
	@RequestMapping("/admin/adminlogout.do")
	public String gotoAdminLoginPage(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:../adminlogin.do";
	}
	
	@RequestMapping("/admin/gotoAdminChangePassPage.do")
	public ModelAndView gotoAdminChangePassPage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) request.getSession().getAttribute(Const.Session.USERNAME);
		User user = this.userService.queryUserByUsername(username);
		map.put(Const.DATA, user);
		return new ModelAndView("backend/user/change_pass", map);
	}
	
	@RequestMapping("/admin/checkOldPassword.do")
	public @ResponseBody Object checkOrignalPassword(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute(Const.Session.USERNAME);
		String oldPass = request.getParameter("oldPass");
		User user = this.userService.queryUserByUsername(username);
		boolean isCorrect = this.userService.isOriginalPassCorrect(oldPass, user.getPassword());
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("is_pass_correct", isCorrect);
		return map;
	}
	
	@RequestMapping("/admin/adminUpdatePassword.do")
	public @ResponseBody Object adminUpdatePassword(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute(Const.Session.USERNAME);
		String newPass = request.getParameter("newPass");
		this.userService.updatePassword(username, newPass);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		return map;
	}

}
