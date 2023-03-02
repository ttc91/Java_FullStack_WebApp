package com.example.demo.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.config.ApiPath;
@RestController
@RequestMapping(value = ApiPath.API_DOMAIN)
public class Test {	
	@GetMapping(value = ApiPath.LOGIN_DOMAIN)
	public ModelAndView getLoginForm(@ModelAttribute("success") String success)
	{
		return new ModelAndView("fe_login");
	}
	
	@GetMapping(value = ApiPath.LOGOUT_DOMAIN)
	public ModelAndView logout(@ModelAttribute("success") String success)
	{
		return new ModelAndView("fe_login");
	}
	
	@PostMapping("/j_spring_security_check")
	public ModelAndView login(@RequestParam("success") String success, RedirectAttributes redirectAttributes)
	{
		if(success == "true") {
			redirectAttributes.addFlashAttribute("success", "Login successfully");
			return new ModelAndView("redirect:/" + ApiPath.API_DOMAIN + ApiPath.TRAINER_DOMAIN + "/page/1");
		}
		return new ModelAndView("redirect:/" + ApiPath.API_DOMAIN + ApiPath.LOGIN_DOMAIN + "?success=Login fail!");
		
	}
	
}
