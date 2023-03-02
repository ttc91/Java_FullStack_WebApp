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
import com.example.demo.services.StatusService;
import com.example.demo.services.dtos.StatusDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class StatusServiceImpl implements StatusService{
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<StatusDto> getAll() {

		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.STATUS_DOMAIN + ApiPath.GET_ALL, HttpMethod.GET, null,
						ResponseDto.class)
				.getBody();
		List<StatusDto> dtos = Arrays.asList(mapper.map(responseDto.getObjList(), StatusDto[].class));

		return dtos;

	}
}
