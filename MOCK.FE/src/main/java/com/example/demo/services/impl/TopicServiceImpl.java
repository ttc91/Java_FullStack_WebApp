package com.example.demo.services.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ApiPath;
import com.example.demo.config.response.ResponseDto;
import com.example.demo.services.TopicService;
import com.example.demo.services.dtos.TopicDto;
import com.example.demo.services.dtos.UniversityDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class TopicServiceImpl implements TopicService{
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<TopicDto> getAll () {
		
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TOPIC_DOMAIN + ApiPath.GET_ALL, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		List<TopicDto> dtos = Arrays.asList(mapper.map(responseDto.getObjList(), TopicDto[].class));
		
		return dtos;
		
	}
	
	@Override
	public List<TopicDto> getTopicByClassId (Integer classId) {
		
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TOPIC_DOMAIN + ApiPath.GET_ALL + "/" + classId, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		List<TopicDto> dtos = Arrays.asList(mapper.map(responseDto.getObjList(), TopicDto[].class));
		
		return dtos;
		
	}
	
}
