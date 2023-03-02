package com.example.demo.controllers;

import java.util.Arrays;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.config.ApiPath;
import com.example.demo.services.CandidateService;
import com.example.demo.services.ChannelService;
import com.example.demo.services.FacultyService;
import com.example.demo.services.LocationService;
import com.example.demo.services.UniversityService;
import com.example.demo.services.dtos.CandidateDto;
import com.example.demo.services.dtos.ChannelDto;
import com.example.demo.services.dtos.FacultyDto;
import com.example.demo.services.dtos.LocationDto;
import com.example.demo.services.dtos.UniversityDto;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(value = ApiPath.API_DOMAIN + ApiPath.CANDIDATE_DOMAIN)
public class CandidateController {

	@Autowired
	CandidateService candidateService;

	@Autowired
	ChannelService channelService;

	@Autowired
	UniversityService universityService;

	@Autowired
	LocationService locationService;

	@Autowired
	FacultyService facultyService;

	@GetMapping(value = "")
	public ModelAndView get(ModelMap model) {
		return new ModelAndView("redirect:/api/v1/candidate/page/1");
	}

	@GetMapping(value = "/page/{currentPage}")
	public ModelAndView getAll(ModelMap model, @PathVariable(name = "currentPage") int currentPage,
			@ModelAttribute("delete") String delete, @ModelAttribute("delete_error") String delete_error) {
		List<CandidateDto> candidateTotal = candidateService.getAll();
		System.out.println(delete);
		List<CandidateDto> candidates = candidateService.getAllPaging(currentPage);
		model.addAttribute("candidates", candidates);

		int totalPage = candidateTotal.size() / 10 + 1;
		System.out.println(candidateTotal.size());
		model.addAttribute("totalPage", totalPage);
		return new ModelAndView("candidate_list", model);
	}

	@GetMapping(value = "/{id}")
	public ModelAndView get(ModelMap model, @PathVariable("id") Integer id,
			@ModelAttribute("transfer") String transfer) {

		CandidateDto candidateDto = candidateService.getOne(id);
		List<ChannelDto> channelDtos = channelService.getAll();
		List<UniversityDto> universityDtos = universityService.getAll();
		List<LocationDto> locationDtos = locationService.getAll();
		List<FacultyDto> facultyDtos = facultyService.getAll();

		model.addAttribute("candidate", candidateDto);
		model.addAttribute("channels", channelDtos);
		model.addAttribute("universities", universityDtos);
		model.addAttribute("locations", locationDtos);
		model.addAttribute("faculties", facultyDtos);

		System.out.println("Channel Dto" + channelDtos);
		System.out.println("University Dtos" + universityDtos);
		System.out.println("Location Dtos" + locationDtos);
		System.out.println("Faculty Dtos" + facultyDtos);

		return new ModelAndView("candidate", model);
	}

	@GetMapping(value = "/update")
	public ModelAndView updateGet(ModelMap model, HttpServletRequest request, @ModelAttribute CandidateDto dto,
			RedirectAttributes redirectedAttributes) {
		String[] idStr = request.getParameterValues("id_delete");
		if (idStr == null || idStr.length > 1) {
			redirectedAttributes.addFlashAttribute("update", false);
			redirectedAttributes.addFlashAttribute("update_error", "choose");
		} else {
			Integer number = Integer.parseInt(idStr[0]);
			return new ModelAndView("redirect:/api/v1/candidate/" + number);
		}
		return new ModelAndView("redirect:/api/v1/candidate/page/1");

	}

	@PostMapping(value = "/update")
	public ModelAndView update(ModelMap model, HttpServletRequest request, @ModelAttribute CandidateDto dto,
			RedirectAttributes redirectedAttributes) {
		Boolean result = candidateService.update(dto);
		System.out.println(dto);
		model.addAttribute("candidate", dto);
		redirectedAttributes.addFlashAttribute("transfer", result);
		return new ModelAndView("redirect:/api/v1/candidate/" + dto.getId());
	}

	@GetMapping(value = "/create")
	public ModelAndView redCreate(ModelMap model) {
		List<ChannelDto> channelDtos = channelService.getAll();
		List<UniversityDto> universityDtos = universityService.getAll();
		List<LocationDto> locationDtos = locationService.getAll();
		List<FacultyDto> facultyDtos = facultyService.getAll();
		CandidateDto candidateDto = new CandidateDto();

		model.addAttribute("channels", channelDtos);
		model.addAttribute("universities", universityDtos);
		model.addAttribute("locations", locationDtos);
		model.addAttribute("faculties", facultyDtos);
		model.addAttribute("candidate", candidateDto);

		System.out.println("Channel Dto" + channelDtos);
		System.out.println("University Dtos" + universityDtos);
		System.out.println("Location Dtos" + locationDtos);
		System.out.println("Faculty Dtos" + facultyDtos);

		return new ModelAndView("candidate", model);
	}

	@PostMapping(value = "/create")
	public ModelAndView create(ModelMap model, @ModelAttribute CandidateDto dto,RedirectAttributes redirectedAttributes) {
		Boolean result = candidateService.create(dto);
		redirectedAttributes.addFlashAttribute("create", result);
		return new ModelAndView("redirect:/api/v1/candidate/create");
	}

	@PostMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, RedirectAttributes redirectedAttributes) {
		String[] idStr = request.getParameterValues("id_delete");
		if (idStr == null) {
			redirectedAttributes.addFlashAttribute("delete", false);
			redirectedAttributes.addFlashAttribute("delete_error", "choose");

		} else {
			int[] ids = Arrays.stream(idStr)
					.mapToInt(Integer::parseInt)
					.toArray();
			Integer[] idInts = Arrays.stream(ids)
					.boxed()
					.toArray(Integer[]::new);
			boolean result = candidateService.deleteList(idInts);
			redirectedAttributes.addFlashAttribute("delete", result);
		}
		return new ModelAndView("redirect:/api/v1/candidate/page/1");
	}

	@PostMapping(value = "transfer")
	public ModelAndView transferCandidate(ModelMap model, @ModelAttribute CandidateDto dto,
			RedirectAttributes redirectedAttributes) {
		// TODO: process POST request
		System.out.println(dto);
		boolean result = candidateService.transferCandidate(dto);
		System.out.println(result);
		redirectedAttributes.addFlashAttribute("transfer", result);
		return new ModelAndView("redirect:/api/v1/candidate/" + dto.getId());
	}

}
