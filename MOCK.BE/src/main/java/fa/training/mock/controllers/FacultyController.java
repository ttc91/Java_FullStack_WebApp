package fa.training.mock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.mock.config.ApiPath;
import fa.training.mock.models.dto.FacultyDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.remotes.services.FacultyService;

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.facultyDomain)
public class FacultyController {
	
	@Autowired
	private FacultyService service;
		
	@PostMapping(ApiPath.insertDomain)
	public ResponseEntity<ResponseDto<BaseDto>> insert(@RequestBody FacultyDto request){
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.create(request), HttpStatus.CREATED);
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}

	}
	
	@PutMapping(ApiPath.updateDomain+"/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> update (@RequestBody FacultyDto request, @PathVariable("id") Integer id){
		
		try {
			request.setMajorId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.update(request), HttpStatus.UPGRADE_REQUIRED);
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping(ApiPath.deleteDomain+"/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> delete(@PathVariable("id") Integer id){
		
		try {
			FacultyDto dto = new FacultyDto();
			dto.setMajorId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.delete(dto), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(ApiPath.getOneDomain+"/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> getOne(@PathVariable("id") Integer id){
		
		try {
			FacultyDto dto = new FacultyDto();
			dto.setMajorId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.getOne(dto), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(ApiPath.getAllDomain)
	public ResponseEntity<ResponseDto<BaseDto>> getAll(){
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.getAll(), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
