package com.mh.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.blog.cache.CacheManager;
import com.mh.blog.entity.Article;
import com.mh.blog.entity.Link;
import com.mh.blog.entity.Profile;
import com.mh.blog.service.impl.CountService;
import com.mh.blog.service.impl.MessageServiceImpl;
import com.mh.blog.util.Const;

@Controller
public class Forward {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CountService countService;
	@Autowired
	private MessageServiceImpl messageService;

	@RequestMapping("/index")
	public ModelAndView gotoFrontIndexPage() {
		this.countService.updateTotalAccess();
		List<Link> lsLink = CacheManager.getCachedLinks(jdbcTemplate);
		List<Article> lsLatest = CacheManager.getCachedLatestNews(jdbcTemplate);
		List<Article> lsTop = CacheManager.getCachedTopNews(jdbcTemplate);
		List<Map<String, Object>> list = CacheManager.getCachedWorkDisplay(jdbcTemplate);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("linkList", lsLink);
		map.put("latestNews", lsLatest);
		map.put("topNews", lsTop);
		map.put("workDisplay", list);
		return new ModelAndView("front/index", map);
	}

	@RequestMapping("/about")
	public ModelAndView gotoFrontAboutPage() {
		Profile profile = CacheManager.getCachedProfile(jdbcTemplate);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.DATA, profile);
		return new ModelAndView("front/about", map);
	}

	@RequestMapping("/blog")
	public String gotoBlogPage() {
		return "front/blog";
	}

	@RequestMapping("/gbook")
	public ModelAndView gotoMessagePage() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.TOTAL_COUNT, this.messageService.getTotalMessageCount());
		Profile profile = CacheManager.getCachedProfile(jdbcTemplate);
		map.put(Const.DATA, profile);
		return new ModelAndView("front/guestbook", map);
	}

	@RequestMapping("/github")
	public ModelAndView gotoGitHubPage() {
		List<Map<String, Object>> list = CacheManager.getCachedWorkDisplay(jdbcTemplate);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.DATA_LIST, list);
		return new ModelAndView("front/github", map);
	}

	@RequestMapping("/moments")
	public String gotoMomentsPage() {
		return "front/moments";
	}

	@RequestMapping("/adminlogin.do")
	public String gotoAdminLoginPage() {
		return "backend/login";
	}

	@RequestMapping("admin/gotoMessagePage.do")
	public String gotoAdminMessagePage(HttpServletRequest request) {
		return "backend/message/list";
	}

	@RequestMapping("admin/gotoMomentsPage.do")
	public String gotoAdminMomentsPage(HttpServletRequest request) {
		return "backend/moments/list";
	}

	@RequestMapping("admin/gotoBlogCategoryPage.do")
	public String gotoAdminBlogCategoryPage(HttpServletRequest request) {
		return "backend/category/list";
	}

	@RequestMapping("admin/gotoBlogArticlePage.do")
	public String gotoAdminBlogArticlePage(HttpServletRequest request) {
		return "backend/article/list";
	}

	@RequestMapping("admin/gotoAddMomentsPage.do")
	public String gotoAddMomentsPage(HttpServletRequest request) {
		return "backend/moments/add";
	}

	@RequestMapping("admin/gotoAddBlogCategoryPage.do")
	public String gotoAdminAddBlogCategoryPage(HttpServletRequest request) {
		return "backend/category/add";
	}

	@RequestMapping("admin/gotoAddBlogArticlePage.do")
	public String gotoAdminAddBlogArticlePage(HttpServletRequest request) {
		return "backend/article/add";
	}

}
