package fa.training.mock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.mock.config.ApiPath;
import fa.training.mock.remotes.entities.Milestone;
import fa.training.mock.remotes.services.TraneeResultService;

@RestController
@RequestMapping(ApiPath.apiDomain + "/traneeResult/{classId}/{traneeId}")
public class TraneeResultController {
	
	@Autowired
	TraneeResultService service;
	
	@GetMapping
	public ResponseEntity<List<Milestone>> insert (@PathVariable("classId") Integer classId, @PathVariable("traneeId") Integer traneeId){
		System.out.println("=============================chao");
		List<Milestone> milestones = service.getMilestone(classId, traneeId);
		return new ResponseEntity<List<Milestone>>(milestones, HttpStatus.CREATED);
	}
}
