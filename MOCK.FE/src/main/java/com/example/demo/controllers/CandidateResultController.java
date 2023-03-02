package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.config.ApiPath;
import com.example.demo.services.CandidateResultService;
import com.example.demo.services.dtos.CandidateResultsDto;
import com.example.demo.services.dtos.EntryTestDto;

@Controller
@RequestMapping(value = ApiPath.API_DOMAIN + ApiPath.CANDIDATE_RESULT_DOMAIN)
public class CandidateResultController {

	@Autowired
	CandidateResultService candidateResultService;

	@GetMapping(value = "/{id}")
	public ModelAndView getAllById(ModelMap model, @PathVariable("id") Integer id, @ModelAttribute("result_update") String result_update) {
		CandidateResultsDto candidateResultsDto = candidateResultService.getByID(id);
		model.addAttribute("candidateResults", candidateResultsDto);
		model.addAttribute("id", id);
		return new ModelAndView("candidate_result", model);
	}

	@PostMapping(value = ApiPath.UPDATE + "/{id}")
	public ModelAndView postMethodName(ModelMap model,@ModelAttribute("candidateResults") CandidateResultsDto dto,@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		// TODO: process POST request
		// System.out.println("UPDATE DTO======> " + dto.getEntryTests().size());
		// System.out.println("UPDATE DTO======> " + dto.getInterviews().size());
		// System.out.println("DTO ENTRYTEST: " + dto.getEntryTests());
		// System.out.println("DTO INTERVIEW: " + dto.getInterviews());
		
		Boolean result=candidateResultService.update(dto, id);
		redirectAttributes.addFlashAttribute("result_update", result);
		return new ModelAndView("redirect:/api/v1/candidate_result/"+id);
	}

}
