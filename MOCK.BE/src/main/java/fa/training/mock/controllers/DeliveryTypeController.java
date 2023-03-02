package fa.training.mock.controllers;

import javax.validation.Valid;

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
import fa.training.mock.config.enums.DeliveryTypeEnum;
import fa.training.mock.models.dto.DeliveryTypeDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.remotes.services.DeliveryTypeService;

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.DELIVERY_TYPE_DOMAIN)
public class DeliveryTypeController {
	
	@Autowired
	DeliveryTypeService service;
	
	@PostMapping(ApiPath.insertDomain)
	public ResponseEntity<ResponseDto<BaseDto>> insert(@RequestBody @Valid DeliveryTypeDto request){
		
		try {
			
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.create(request), HttpStatus.CREATED);
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping(ApiPath.updateDomain)
	public ResponseEntity<ResponseDto<BaseDto>> update(@RequestBody @Valid DeliveryTypeDto request){
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.update(request), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@DeleteMapping(ApiPath.deleteDomain + "/{deliveryTypeName}")
	public ResponseEntity<ResponseDto<BaseDto>> delete(@PathVariable("deliveryTypeName") DeliveryTypeEnum deliveryTypeName){
		
		try {
			
			DeliveryTypeDto dto = new DeliveryTypeDto();
			dto.setDeliveryTypeName(deliveryTypeName);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.delete(dto), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(ApiPath.getOneDomain + "/{deliveryTypeName}")
	public ResponseEntity<ResponseDto<BaseDto>> getOne(@PathVariable("deliveryTypeName") DeliveryTypeEnum deliveryTypeName){
		
		try {
			
			DeliveryTypeDto dto = new DeliveryTypeDto();
			dto.setDeliveryTypeName(deliveryTypeName);
			return new ResponseEntity<ResponseDto<BaseDto>>(service.getOne(dto), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(ApiPath.getAllDomain)
	public ResponseEntity<ResponseDto<BaseDto>> getAll(){
		
		try {
			
			return new ResponseEntity<ResponseDto<BaseDto>>(service.getAll(), HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
