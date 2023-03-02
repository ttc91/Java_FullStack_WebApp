package fa.training.mock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.mock.config.ApiPath;
import fa.training.mock.models.dto.UserDto;
import fa.training.mock.models.dto.base.BaseDto;
import fa.training.mock.models.dto.base.ResponseDto;
import fa.training.mock.remotes.services.UserService;


@RestController
@RequestMapping(value =  ApiPath.apiDomain + ApiPath.USER_DOMAIN)
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping(value = ApiPath.LOGIN_DOMAIN)
	public ResponseEntity<ResponseDto<BaseDto>> login (@RequestBody UserDto user){
		
		try {		
			return new ResponseEntity<ResponseDto<BaseDto>>(service.login(user), HttpStatus.OK);	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
		
	}
	
	@GetMapping(value = ApiPath.getOneDomain)
	public ResponseEntity<ResponseDto<BaseDto>> login (@RequestParam("username") String username){
		
		try {		
			return new ResponseEntity<ResponseDto<BaseDto>>(service.findUserByUsername(username), HttpStatus.OK);	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
		
	}
	
}
