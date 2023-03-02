package fa.training.mock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.mock.config.ApiPath;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.models.dto.res.trainingResult.TrainingResultDto;
import fa.training.mock.remotes.services.TrainingResultService;

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.traningResultDomain)
public class TrainingResultController {
	
	@Autowired
	private TrainingResultService trainingResultService;
	
	@GetMapping(ApiPath.getOneDomain+"/{classId}/{traineeId}")
	public ResponseEntity<ResponseDto<BaseDto>> getOne(@PathVariable("traineeId") Integer traineeId,
			@PathVariable("classId") Integer classId){
		try {
			return new ResponseEntity<ResponseDto<BaseDto>>(trainingResultService.getOne(classId, traineeId), HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
	}

	@PutMapping(ApiPath.updateDomain)
	public ResponseEntity<ResponseDto<BaseDto>> update(@RequestBody TrainingResultDto traineeDto){
		System.out.println(traineeDto.toString());
		
		return new ResponseEntity<ResponseDto<BaseDto>>(trainingResultService.update(traineeDto), HttpStatus.CREATED);
	}
}
