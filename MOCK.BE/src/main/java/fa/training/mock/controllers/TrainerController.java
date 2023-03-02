package fa.training.mock.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.mock.config.ApiPath;
import fa.training.mock.models.dto.TrainerDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.remotes.services.TrainerService;

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.TRAINER_DOMAIN)
public class TrainerController {

	@Autowired
	TrainerService service;
	
	@PostMapping(ApiPath.insertDomain)
	public ResponseEntity<ResponseDto<BaseDto>> insert(@RequestBody @Valid TrainerDto request) throws Exception {

		try {

			return new ResponseEntity<ResponseDto<BaseDto>>(service.create(request), HttpStatus.CREATED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(ApiPath.updateDomain)
	public ResponseEntity<ResponseDto<BaseDto>> update(@RequestBody TrainerDto request) throws Exception {
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.update(request), HttpStatus.OK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(ApiPath.deleteDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> delete(@PathVariable("id") Integer request) throws Exception{
		
		try {
			
			TrainerDto dto = new TrainerDto();
			dto.setTrainerId(request);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.delete(dto), HttpStatus.OK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(ApiPath.getOneDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> getOne(@PathVariable("id") Integer request) throws Exception{
		
		try {
			
			TrainerDto dto = new TrainerDto();
			dto.setTrainerId(request);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.getOne(dto), HttpStatus.OK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(ApiPath.getAllDomain)
	public ResponseEntity<ResponseDto<BaseDto>> getAll(){
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.getAll(), HttpStatus.OK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(ApiPath.getAllDomain +"/page/{currentPage}")
	public ResponseEntity<ResponseDto<BaseDto>> getAllPaging(@PathVariable("currentPage") Integer currentPage) {
		try {

			return new ResponseEntity<ResponseDto<BaseDto>>(service.getAllPaging(currentPage), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
