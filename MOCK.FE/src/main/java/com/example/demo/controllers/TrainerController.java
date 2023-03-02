package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.ApiPath;
import com.example.demo.config.security.JwtTokenProvider;
import com.example.demo.config.security.MyUserDetails;
import com.example.demo.services.TrainerService;
import com.example.demo.services.dtos.TrainerDto;

@Controller
@RequestMapping(value = ApiPath.API_DOMAIN + ApiPath.TRAINER_DOMAIN)
public class TrainerController {

	@Autowired
	TrainerService service;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@GetMapping(value = "/{id}")
	public ModelAndView get (ModelMap model,  @PathVariable("id") Integer id ){
		
		TrainerDto dto = service.getOne(id);
		model.addAttribute("trainer", dto);
		
		return new ModelAndView("fe_trainer_profile_update", model);
	}
	
	@GetMapping(value = "/create")
	public ModelAndView create (ModelMap model){
		
		TrainerDto dto = new TrainerDto();
		model.addAttribute("trainer", dto);
		return new ModelAndView("fe_trainer_profile_create", model);
	
	}
	
	@PostMapping(value = "/create")
	public ModelAndView create (ModelMap model, @ModelAttribute TrainerDto dto){
		
		service.create(dto);
		return new ModelAndView("redirect:/api/v1/trainer/get_all_trainers");
	
	}
	
	@PostMapping()
	public ModelAndView update (ModelMap model, @ModelAttribute TrainerDto dto){
		
		service.update(dto);
		model.addAttribute("trainer", dto);
		
		return new ModelAndView("redirect:/api/v1/trainer/get_all_trainers");
	}
	
	@GetMapping(value = "/delete_trainer/{id}")
	public ModelAndView delete (@PathVariable("id") Integer id){
		
		service.delete(id);
		return new ModelAndView("redirect:/api/v1/trainer/get_all_trainers");
	}
	
	@GetMapping(value = "/get_all_trainers")
	public ModelAndView getAll (ModelMap model){
		
		return new ModelAndView("redirect:/api/v1/trainer/page/1");
	}
	
	@GetMapping(value = "/page/{currentPage}")
	public ModelAndView getAll (ModelMap model, @PathVariable(name = "currentPage") int currentPage){
		
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String token = jwtTokenProvider.generateToken(userDetails);
		System.out.println(token);
		
		List<TrainerDto> dtos = service.getAll();
		
		List<TrainerDto> pagingdto = service.getAllPaging(currentPage);
		model.addAttribute("list_trainer", pagingdto);
		
		int totalPage = dtos.size() / 4 + 1;
		model.addAttribute("totalPage", totalPage);	
		return new ModelAndView("fe_trainer_list", model);
	
	}
	
}
