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
import com.example.demo.services.MilestoneService;
import com.example.demo.services.dtos.MilestoneDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class MilestoneServiceImpl implements MilestoneService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<MilestoneDto> getMilestoneByClass(Integer classId) {

		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.MILESTONE_DOMAIN + ApiPath.GET_ALL + "_by_class/" + classId,
						HttpMethod.GET, null, ResponseDto.class)
				.getBody();
		List<MilestoneDto> dtos = Arrays.asList(mapper.map(responseDto.getObjList(), MilestoneDto[].class));

		return dtos;

	}

	
	@Override
	public List<MilestoneDto> getAll () {
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.MILESTONE_DOMAIN + ApiPath.GET_ALL, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		List<MilestoneDto> dto = Arrays.asList(mapper.map(responseDto.getObjList(), MilestoneDto[].class));
		return dto;
		
	}
}
