package fa.training.mock.controllers;

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
import fa.training.mock.config.enums.BudgetEnum;
import fa.training.mock.models.dto.BudgetDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.remotes.services.BudgetService;

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.BUDGET_DOMAIN)
public class BudgetController {
	
	@Autowired
	BudgetService service;
	
	@PostMapping(value = ApiPath.insertDomain)
	public ResponseEntity<ResponseDto<BaseDto>> insert(@RequestBody @Valid BudgetDto request){
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.create(request), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping(value = ApiPath.updateDomain)
	public ResponseEntity<ResponseDto<BaseDto>> update(@RequestBody @Valid BudgetDto request){
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.update(request), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@DeleteMapping(value = ApiPath.deleteDomain + "/{budgetCode}")
	public ResponseEntity<ResponseDto<BaseDto>> delete(@PathVariable("budgetCode") BudgetEnum budgetCode){
		
		try {
			
			BudgetDto dto = new BudgetDto();
			dto.setBudgetCode(budgetCode);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.delete(dto), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping(value = ApiPath.getOneDomain + "/{budgetCode}")
	public ResponseEntity<ResponseDto<BaseDto>> getOne(@PathVariable("budgetCode") BudgetEnum budgetCode){
		
		try {
			
			BudgetDto dto = new BudgetDto();
			dto.setBudgetCode(budgetCode);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.getOne(dto), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping(value = ApiPath.getAllDomain)
	public ResponseEntity<ResponseDto<BaseDto>> getOne(){
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.getAll(), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
