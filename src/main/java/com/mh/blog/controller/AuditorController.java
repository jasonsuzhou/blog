package com.mh.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mh.blog.entity.Auditor;
import com.mh.blog.service.impl.AuditorServiceImpl;
import com.mh.blog.util.Const;

@Controller
public class AuditorController {
	
	@Autowired
	private AuditorServiceImpl auditorService;

	@RequestMapping("admin/adminQueryAuditor.do")
	public @ResponseBody Object adminQueryAuditor(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String curPage = request.getParameter(Const.PARA_CUR_PAGE);
		String perPage = request.getParameter(Const.PARA_PER_PAGE);
		List<Auditor> dataList = this.auditorService.queryAuditor(Integer.parseInt(curPage), Integer.parseInt(curPage));
		int totalCount = this.auditorService.getTotalCount();
		map.put(Const.DATA_LIST, dataList);
		map.put(Const.TOTAL_COUNT, totalCount);
		return map;
	}
	
	@RequestMapping("admin/adminDeleteAuditor.do")
	public String adminDeleteAuditor(String id) {
		this.auditorService.deleteById(Integer.parseInt(id));
		return "redirect:/admin/gotoAdminAuditorPage.do";
	}
	
	@RequestMapping("admin/gotoAdminAuditorPage.do")
	public String gotoAdminAuditorPage() {
		return "backend/auditor/list";
	}
	
	@RequestMapping("admin/gotoEditAuditorPage.do")
	public ModelAndView gotoEditPage(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Auditor data = this.auditorService.queryAuditorById(Integer.parseInt(id));
		map.put(Const.DATA, data);
		return new ModelAndView("backend/auditor/edit", map);
	} 
	
	@RequestMapping("admin/adminUpdateAuditor.do")
	public String adminUpdateAuditor(HttpServletRequest request) {
		String id = request.getParameter("id");
		String isBlack = request.getParameter("isBlack");
		String attemptTimes = request.getParameter("attempTimes");
		this.auditorService.resetBlackIP(Integer.parseInt(id), Integer.parseInt(attemptTimes), isBlack);
		return "redirect:/admin/gotoAdminAuditorPage.do";
	}

}
