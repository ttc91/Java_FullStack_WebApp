package com.example.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ApiPath;
import com.example.demo.config.response.ResponseDto;
import com.example.demo.services.TraineeProfileService;
import com.example.demo.services.dtos.TraineeDto;
import com.example.demo.services.dtos.TrainerDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class TraineeProfileServiceImpl implements TraineeProfileService{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public TraineeDto getOne (Integer id) {
		
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ONE + "/" + id, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		TraineeDto dto = mapper.map(responseDto.getObj(), TraineeDto.class);
		
		return dto;
		
	}
	
	@Override
	public Boolean update (TraineeDto dto) {
		
		try {
			
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.UPDATE + "/" + dto.getTraineeId(), 
											HttpMethod.PUT, new HttpEntity<>(dto), ResponseDto.class).getBody();
			
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
}
