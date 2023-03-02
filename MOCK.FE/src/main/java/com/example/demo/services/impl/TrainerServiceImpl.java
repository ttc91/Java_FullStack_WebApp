package com.example.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ApiPath;
import com.example.demo.config.response.ResponseDto;
import com.example.demo.services.TrainerService;
import com.example.demo.services.dtos.TrainerDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class TrainerServiceImpl implements TrainerService{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public TrainerDto getOne (Integer id) {
		
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINER_DOMAIN + ApiPath.GET_ONE + "/" + id, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		TrainerDto dto = mapper.map(responseDto.getObj(), TrainerDto.class);
		
		return dto;
		
	}
	
	@Override
	public Boolean update (TrainerDto dto) {
		
		try {
			
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINER_DOMAIN + ApiPath.UPDATE, 
											HttpMethod.POST, new HttpEntity<>(dto), ResponseDto.class).getBody();
			
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public Boolean create (TrainerDto dto) {
		
		try {
			
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINER_DOMAIN + ApiPath.INSERT, 
											HttpMethod.POST, new HttpEntity<>(dto), ResponseDto.class).getBody();
			
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public Boolean delete (Integer id) {
		
		try {	
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINER_DOMAIN + ApiPath.DELETE + "/" + id, 
											HttpMethod.DELETE, null, ResponseDto.class).getBody();
			
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public List<TrainerDto> getAll(){
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINER_DOMAIN + ApiPath.GET_ALL, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		List<TrainerDto> list = responseDto.getObjList().stream().map(obj -> mapper.map(obj, TrainerDto.class)).collect(Collectors.toList());
	
		return list;
	}
	
	@Override
	public List<TrainerDto> getAllPaging(Integer currentPage){
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINER_DOMAIN + ApiPath.GET_ALL + "/page/" + currentPage, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		List<TrainerDto> list = responseDto.getObjList().stream().map(obj -> mapper.map(obj, TrainerDto.class)).collect(Collectors.toList());
	
		return list;
	}
}
