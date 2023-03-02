package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.ApiPath;
import com.example.demo.config.utils.MultipartInputStreamFileResource;
import com.example.demo.services.FacultyService;
import com.example.demo.services.MilestoneService;
import com.example.demo.services.StatusService;
import com.example.demo.services.TopicService;
import com.example.demo.services.TraineeService;
import com.example.demo.services.UniversityService;
import com.example.demo.services.dtos.TraineeDtoList;

@Controller
@RequestMapping(value = ApiPath.API_DOMAIN + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL)

public class TraineeController {
	@Autowired
	TraineeService service;

	@Autowired
	UniversityService universityService;

	@Autowired
	FacultyService facultyService;

	@Autowired
	StatusService statusService;

	@Autowired
	MilestoneService milestoneService;

	@Autowired
	TopicService topicService;

	@GetMapping(value = "/{classBatchId}")
	public ModelAndView get(ModelMap model, @PathVariable("classBatchId") Integer classBatchId) {
		TraineeDtoList traineeDtoList = new TraineeDtoList(service.getTraineeByClassBatch(classBatchId));
		TraineeDtoList traineeDtoListt = new TraineeDtoList(service.getByWaitingForClass(classBatchId));
		System.out.println(traineeDtoListt.getTraineeDtos());
		model.addAttribute("t", traineeDtoList);
		model.addAttribute("tt", traineeDtoListt);
		model.addAttribute("traineeIds", new ArrayList<Integer>());
		model.addAttribute("u", universityService.getAll());
		model.addAttribute("f", facultyService.getAll());
		model.addAttribute("mi", milestoneService.getMilestoneByClass(classBatchId));
		model.addAttribute("s", statusService.getAll());
		model.addAttribute("topic", topicService.getTopicByClassId(classBatchId));
		model.addAttribute("classId", classBatchId);
		return new ModelAndView("traineeList", model);
	}

	@GetMapping
	public ModelAndView get(ModelMap model) {
		model.addAttribute("t", service.getAll());
		return new ModelAndView("traineeListing", model);
	}

	@PostMapping(params = "remove")
	public String remove(ModelMap model, HttpServletRequest request) {
		String[] traineeIds = request.getParameterValues("traineeId");
		List<Integer> traineeIdss = new ArrayList<>();
		for (String k : traineeIds) {
			traineeIdss.add(Integer.parseInt(k));
			System.out.println(k);
		}
		String classId = request.getParameter("classId");
		boolean flag = service.removeTraineesFromClass(traineeIdss, Integer.parseInt(classId));
		return "redirect:/api/v1" + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + "/" + classId;
	}
	
	@PostMapping(params = "update")
	public String update(ModelMap model, HttpServletRequest request) {
		String traineeId = request.getParameter("traineeId");
		return "redirect:/api/v1" + ApiPath.TRAINEE_DOMAIN + "/" + traineeId;
	}

	@PostMapping(params = "allocation")
	public String updateAllocation(ModelMap model, HttpServletRequest request,
			@ModelAttribute("t") TraineeDtoList list) {
		Boolean flag = service.updateTraineeLocation(list.getTraineeDtos());
		Integer classId = Integer.parseInt(request.getParameter("classId"));
		return "redirect:/api/v1" + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + "/" + classId;
	}

	@PostMapping(params = "status")
	public String updateTraineeStatus(ModelMap model, HttpServletRequest request,
			@ModelAttribute("t") TraineeDtoList list) {
		Boolean flag = service.updateTraineeStatus(list.getTraineeDtos());
		Integer classId = Integer.parseInt(request.getParameter("classId"));
		return "redirect:/api/v1" + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + "/" + classId;
	}

	@PostMapping(params = "topicMark")
	public String updateTopicMark(ModelMap model, HttpServletRequest request,
			@ModelAttribute("t") TraineeDtoList list) {
		Boolean flag = service.updateTopicMark(list.getTraineeDtos());
		Integer classId = Integer.parseInt(request.getParameter("classId"));
		return "redirect:/api/v1" + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + "/" + classId;
	}

	@PostMapping(params = "rewardPenalty")
	public String updateRewardPenalty(ModelMap model, HttpServletRequest request,
			@ModelAttribute("t") TraineeDtoList list) {
		Boolean flag = service.updateRewardPenalty(list.getTraineeDtos());
		Integer classId = Integer.parseInt(request.getParameter("classId"));		
		return "redirect:/api/v1" + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + "/" + classId;
	}

	@GetMapping(value = "/update/{traineeId}")
	public ModelAndView getTrainee(ModelMap model, HttpServletRequest request,
			@PathVariable("traineeId") Integer traineeId) {

		return new ModelAndView("traineeList", model);
	}
	
	@PostMapping(value="/attendenceStatus/update")
	public String updateTrainee(@ModelAttribute("t") TraineeDtoList t, HttpServletRequest request) {
		System.out.println(request.getAttribute("classId"));
		Boolean resultDto = service.updateAttendentStatus(t);
		return "redirect:"+ApiPath.API_DOMAIN +ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + "/" + request.getParameter("classId");
	}

	@PostMapping(value = "/addTraineeToClass")
	public String addTraineeToClass(ModelMap model, HttpServletRequest request) {
		String[] traineeIds = request.getParameterValues("traineeId");
		List<Integer> traineeIdss = new ArrayList<>();
		System.out.println("=========");
		System.out.println("=========" + traineeIds.length);
		for (int i = 0; i < traineeIds.length; i++) {
			traineeIdss.add(Integer.parseInt(traineeIds[i]));
		}
		String classId = request.getParameter("classId");
		System.out.println(classId);
		service.addTraineeToClass(traineeIdss, Integer.parseInt(classId));
		return "redirect:/api/v1" + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + "/" + classId;
	}

	@GetMapping(value = "/excel/import/{classId}")
	public ModelAndView getImportTrainee(ModelMap model, @PathVariable("classId") Integer classId) {

		model.addAttribute("classId", classId);
		return new ModelAndView("importTrainee", model);
	}

	@PostMapping(value = "/excel/import/{classId}")
	public ModelAndView importTrainee(ModelMap model, @RequestParam("file") MultipartFile file, @PathVariable("classId") Integer classId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		try {
			body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		String serverUrl = "http://localhost:8080/api/v1/class_batch/excel/"+classId;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
		return new ModelAndView("importTrainee", model);
	}

}
