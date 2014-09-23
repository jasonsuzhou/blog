package com.mh.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mh.blog.entity.Link;
import com.mh.blog.service.impl.LinkServiceImpl;
import com.mh.blog.util.Const;

@Controller
public class LinkController {

	@Autowired
	private LinkServiceImpl linkService;

	@RequestMapping("admin/gotoAddLinkPage.do")
	public String gotoAdminAddLinkPage(HttpServletRequest request) {
		return "backend/link/add";
	}

	@RequestMapping("admin/gotoLinkPage.do")
	public String gotoAdminLinkPage(HttpServletRequest request) {
		return "backend/link/list";
	}

	@RequestMapping("admin/gotoEditLinkPage.do")
	public ModelAndView gotoAdminEditLinkPage(String id) {
		Link link = this.linkService.queryLinkById(Integer.parseInt(id));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.DATA, link);
		return new ModelAndView("backend/link/edit", map);
	}

	@RequestMapping("admin/adminQueryLinks.do")
	public @ResponseBody
	Object adminQueryLinks(String curPage) {
		int totalCount = this.linkService.getTotalCount();
		List<Link> list = this.linkService.getPageLinks(Integer.parseInt(curPage), Const.PER_PAGE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.DATA_LIST, list);
		map.put(Const.TOTAL_COUNT, totalCount);
		return map;
	}

	@RequestMapping("admin/addLinkCommit.do")
	public String addLink(HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		// redirectAttributes.addFlashAttribute("test", "test");
		Link link = new Link();
		BeanUtils.populate(link, request.getParameterMap());
		this.linkService.addLink(link);
		return "redirect:/admin/gotoLinkPage.do";
	}

	@RequestMapping("admin/adminUpdateLink.do")
	public String adminUpdateLink(HttpServletRequest request) throws Exception {
		Link link = new Link();
		BeanUtils.populate(link, request.getParameterMap());
		this.linkService.updateLink(link);
		return "redirect:/admin/gotoLinkPage.do";
	}

	@RequestMapping("admin/adminDeleteLink.do")
	public String adminDeleteLink(String id) {
		this.linkService.deleteLinkById(Integer.parseInt(id));
		return "redirect:/admin/gotoLinkPage.do";
	}

}
