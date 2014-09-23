package com.mh.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.blog.entity.Profile;
import com.mh.blog.service.impl.ProfileServiceImpl;


@Controller
public class ProfileController {
	
	@Autowired
	private ProfileServiceImpl profileService;
	
	@RequestMapping("admin/gotoEditProfilePage.do")
	public ModelAndView gotoEditPage() {
		Profile profile = this.profileService.queryProfile();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", profile);
		return new ModelAndView("backend/profile/edit", map);
	}
	
	@RequestMapping("admin/updateProfileCommit.do")
	public String updateProfile(HttpServletRequest request) throws Exception {
		Profile profile = new Profile();
		BeanUtils.populate(profile, request.getParameterMap());
		this.profileService.updateProfile(profile);
		return "redirect:gotoEditProfilePage.do";
	}
	
}
