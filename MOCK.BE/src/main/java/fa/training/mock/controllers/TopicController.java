package fa.training.mock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.mock.config.ApiPath;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.remotes.services.TopicService;

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.topicDomain)
public class TopicController {
	
	@Autowired
	TopicService service;
	
	@GetMapping(ApiPath.getAllDomain + "/{classId}")
	public ResponseEntity<ResponseDto<BaseDto>> getAllByClassId(@PathVariable("classId") Integer classId) {

		try {

			return new ResponseEntity<ResponseDto<BaseDto>>(service.findAllByClassId(classId), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}
