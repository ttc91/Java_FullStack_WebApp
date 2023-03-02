package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ApiPath;
import com.example.demo.config.response.ResponseDto;
import com.example.demo.services.AllowanceGroupService;
import com.example.demo.services.dtos.AllowanceGroupDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class AllowanceGroupServiceImpl implements AllowanceGroupService{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<AllowanceGroupDto> getAll() {
		
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.ALLOWANCEGROUP_DOMAIN + ApiPath.GET_ALL, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		List<AllowanceGroupDto> dtos = Arrays.asList(mapper.map(responseDto.getObjList(), AllowanceGroupDto[].class));
		
		return dtos;
		
	}
	
}
