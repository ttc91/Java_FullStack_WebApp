package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ApiPath;
import com.example.demo.config.response.ResponseDto;
import com.example.demo.services.TrainingResultService;
import com.example.demo.services.dtos.MilestoneMarkDto;
import com.example.demo.services.dtos.TopicMarkDto;
import com.example.demo.services.dtos.TrainingResultDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class TrainingResultServiceImpl implements TrainingResultService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

	@Override
	public TrainingResultDto get(Integer classBatchId, Integer traineeId) {

		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAININGRESULT_DOMAIN
				+ ApiPath.GET_ONE + "/" + classBatchId + "/" + traineeId, HttpMethod.GET, null, ResponseDto.class)
				.getBody();

		return mapper.map(responseDto.getObj(), TrainingResultDto.class);
	}

	@Override

	public TrainingResultDto update(TrainingResultDto dto) {
		List<TopicMarkDto> topicMarkDtos = new ArrayList<TopicMarkDto>();
		for (MilestoneMarkDto milestoneMark : dto.getMilestoneMarkDtos()) {
			if (!milestoneMark.getTopicMarkDtos().isEmpty()) {
				topicMarkDtos.addAll(milestoneMark.getTopicMarkDtos());
			}
		}
		dto.setTopicMarkDtos(topicMarkDtos);
		System.out.println(new HttpEntity<TrainingResultDto>(dto));
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAININGRESULT_DOMAIN
				+ ApiPath.UPDATE, HttpMethod.PUT, new HttpEntity<TrainingResultDto>(dto), ResponseDto.class)
				.getBody();

		return mapper.map(responseDto.getObj(), TrainingResultDto.class);
	}
}
