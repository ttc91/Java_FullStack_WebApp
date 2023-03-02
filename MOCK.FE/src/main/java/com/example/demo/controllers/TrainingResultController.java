package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.ApiPath;
import com.example.demo.services.MilestoneService;
import com.example.demo.services.TrainingResultService;
import com.example.demo.services.dtos.MilestoneDto;
import com.example.demo.services.dtos.MilestoneMarkDto;
import com.example.demo.services.dtos.TopicMarkDto;
import com.example.demo.services.dtos.TrainingResultDto;

@Controller
@RequestMapping(value = ApiPath.API_DOMAIN + ApiPath.TRAININGRESULT_DOMAIN)
public class TrainingResultController {

	@Autowired
	TrainingResultService service;
	
	@Autowired
	MilestoneService milestoneService;
	
	@GetMapping(value = "/{classBatchId}/{traineeId}")
	public ModelAndView get(ModelMap model, @PathVariable("classBatchId") Integer classBatchId, @PathVariable("traineeId") Integer traineeId) {
		TrainingResultDto resultDto = service.get(classBatchId, traineeId);
		List<MilestoneDto> milestoneDtos = milestoneService.getAll();
		
		System.out.println( "======================="+ resultDto);
		model.addAttribute("d", resultDto);
		model.addAttribute("milestones", milestoneDtos);
		return new ModelAndView("trainingResult", model);
	}
	
	@PostMapping
	public String post(ModelMap model, HttpServletRequest request, @ModelAttribute("d") TrainingResultDto dto) {
		System.out.println( "======================="+ dto);
		TrainingResultDto resultDto = service.update(dto);
		model.addAttribute("d", resultDto);
		
		return "redirect:"+ApiPath.API_DOMAIN + ApiPath.TRAININGRESULT_DOMAIN + "/" +dto.getClassId()+ "/" +dto.getTraineeId();
	}
	
}
