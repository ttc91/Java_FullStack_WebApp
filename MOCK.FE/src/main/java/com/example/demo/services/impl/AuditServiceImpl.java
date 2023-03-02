package com.example.demo.services.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ApiPath;
import com.example.demo.config.response.ResponseDto;
import com.example.demo.services.AuditService;
import com.example.demo.services.dtos.AuditDto;
import com.example.demo.services.dtos.base.BaseDto;
@Service
public class AuditServiceImpl implements AuditService{
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<AuditDto> getAll() {
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.AUDITDOMAIN + ApiPath.GET_ALL, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		List<AuditDto> dto = Arrays.asList(mapper.map(responseDto.getObjList(), AuditDto[].class));
		return dto;
	}

	@Override
	public AuditDto getOne(Integer id) {

		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.AUDITDOMAIN + ApiPath.GET_ONE + "/" + id, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		AuditDto dto = mapper.map(responseDto.getObj(), AuditDto.class);	
		return dto;
	}

	@Override
	public Boolean create(AuditDto dto) {
			try {
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.AUDITDOMAIN + ApiPath.INSERT, 
											HttpMethod.POST, new HttpEntity<>(dto), ResponseDto.class).getBody();
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean delete(Integer id) {
			try {
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.AUDITDOMAIN + ApiPath.DELETE + "/" + id, 
											HttpMethod.DELETE, null, ResponseDto.class).getBody();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean update(AuditDto dto, Integer id) {
		try {
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.AUDITDOMAIN + ApiPath.UPDATE  + "/" + id, 
											HttpMethod.PUT, new HttpEntity<>(dto), ResponseDto.class).getBody();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
