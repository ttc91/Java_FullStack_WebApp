package fa.training.mock.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.mock.config.ApiPath;
import fa.training.mock.models.dto.BudgetItemDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.remotes.services.BudgetItemService;

@RestController
@RequestMapping(value = ApiPath.apiDomain + ApiPath.BUDGET_ITEM_DOMAIN)
public class BudgetItemController {

	@Autowired
	BudgetItemService service;
	
	@PostMapping(value = ApiPath.insertDomain)
	public ResponseEntity<ResponseDto<BaseDto>> insert (@RequestBody @Valid BudgetItemDto request) {
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.create(request), HttpStatus.CREATED);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping(value = ApiPath.INSERT_LIST_DOMAIN)
	public ResponseEntity<ResponseDto<BaseDto>> insertList (@RequestBody @Valid List<BudgetItemDto> request) {
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.createListDto(request), HttpStatus.CREATED);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping(value = ApiPath.updateDomain)
	public ResponseEntity<ResponseDto<BaseDto>> update (@RequestBody @Valid BudgetItemDto request) {
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.update(request), HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:8081/")
	@DeleteMapping(value = ApiPath.deleteDomain + "/{id}")
	public ResponseEntity<ResponseDto<BaseDto>> update (@PathVariable("id") Integer id) {
		
		try {
			
			BudgetItemDto dto = new BudgetItemDto();
			dto.setId(id);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.delete(dto), HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value = "/get_all_by_class_batch")
	public ResponseEntity<ResponseDto<BaseDto>> getAllByClassBatch (@RequestBody @Valid BudgetItemDto request) {
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.getAllByClassBatch(request), HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
