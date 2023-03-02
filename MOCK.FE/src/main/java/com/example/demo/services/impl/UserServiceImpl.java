package com.example.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ApiPath;
import com.example.demo.config.response.ResponseDto;
import com.example.demo.services.UserService;
import com.example.demo.services.dtos.UserDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public UserDto login(BaseDto dto) {
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.USER_DOMAIN + ApiPath.LOGIN_DOMAIN, 
											HttpMethod.POST, new HttpEntity<>(dto), ResponseDto.class).getBody();
		UserDto user = mapper.map(responseDto.getObj(), UserDto.class);
		return user;

		
	}
	
	@Override
	public UserDto findByUsername(String username) {
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.USER_DOMAIN + ApiPath.GET_ONE + "?username=" + username, 
											HttpMethod.GET, new HttpEntity<>(username), ResponseDto.class).getBody();
		UserDto user = mapper.map(responseDto.getObj(), UserDto.class);
		return user;

		
	}

}
