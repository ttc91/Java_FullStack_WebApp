package com.example.demo.controllers;

import java.io.IOException;

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
import com.example.demo.services.AllowanceGroupService;
import com.example.demo.services.FacultyService;
import com.example.demo.services.TraineeProfileService;
import com.example.demo.services.UniversityService;
import com.example.demo.services.dtos.TraineeDto;

@Controller
@RequestMapping(value = ApiPath.API_DOMAIN + ApiPath.TRAINEE_DOMAIN)
public class TraineeProfileController {

	@Autowired
	TraineeProfileService service;

	@Autowired
	FacultyService facultyService;

	@Autowired
	UniversityService universityService;

	@Autowired
	AllowanceGroupService allowanceGroupService;

	@GetMapping(value = "/{id}")
	public ModelAndView get(ModelMap model, @PathVariable("id") Integer id) {

		TraineeDto dto = service.getOne(id);
		
		model.addAttribute("t", dto);
		model.addAttribute("f", facultyService.getAll());
		model.addAttribute("u", universityService.getAll());
		model.addAttribute("a", allowanceGroupService.getAll());

		return new ModelAndView("traineeProfile", model);
	}

	@PostMapping
	public String update(ModelMap model, @ModelAttribute("t") TraineeDto dto) {
		service.update(dto);

		model.addAttribute("f", facultyService.getAll());
		model.addAttribute("u", universityService.getAll());
		model.addAttribute("a", allowanceGroupService.getAll());
		model.addAttribute("t", dto);

		return "redirect:/api/v1/trainee/" + dto.getTraineeId();
	}

	@Autowired
	RestTemplate restTemplate = new RestTemplate();

	@PostMapping("/excel")
	public String importFile(@RequestParam("file") MultipartFile file) {
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
		
		String serverUrl = "http://localhost:8080/api/v1/class_batch/excel/1";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate
		  .postForEntity(serverUrl, requestEntity, String.class);
		return "redirect:/api/v1/trainee/1";
	}
}


