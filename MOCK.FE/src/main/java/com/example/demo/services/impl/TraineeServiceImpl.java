package com.example.demo.services.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ApiPath;
import com.example.demo.config.response.ResponseDto;
import com.example.demo.services.TraineeService;
import com.example.demo.services.dtos.TraineeDto;
import com.example.demo.services.dtos.TraineeDtoList;
import com.example.demo.services.dtos.TrainingResultDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class TraineeServiceImpl implements TraineeService{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<TraineeDto> getTraineeByClassBatch (Integer classPatchId) {
		
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + "/" + classPatchId, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		List<TraineeDto> dtos = Arrays.asList(mapper.map(responseDto.getObjList(), TraineeDto[].class));
		
		return dtos;
		
	}
	
	@Override
	public List<TraineeDto> getAll () {
		
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL, 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		List<TraineeDto> dtos = Arrays.asList(mapper.map(responseDto.getObjList(), TraineeDto[].class));
		
		return dtos;
		
	}
	
	@Override
	public List<TraineeDto> getByWaitingForClass (Integer classBatchId) {
		
		
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + "/" + classBatchId + ApiPath.STATUS_DOMAIN +"/WaitingforClass", 
											HttpMethod.GET, null, ResponseDto.class).getBody();
		
		List<TraineeDto> dtos = Arrays.asList(mapper.map(responseDto.getObjList(), TraineeDto[].class));
		
		return dtos;
		
	}
	
	@Override
	public Boolean updateTraineeLocation (List<TraineeDto> traineeDtos) {
		
		System.out.println("==========================");
		System.out.println(traineeDtos);
		
		@SuppressWarnings("unchecked")
		ResponseDto<List<BaseDto>> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + ApiPath.ALLOCATION_DOMAIN, 
											HttpMethod.PUT, new HttpEntity<>(traineeDtos), ResponseDto.class).getBody();
		
		
		return responseDto.getStatusCode() == 200 ? true : false;
		
	}
	
	@Override
	public Boolean updateTraineeStatus (List<TraineeDto> traineeDtos) {
		
		System.out.println("==========================");
		System.out.println(traineeDtos);
		
		@SuppressWarnings("unchecked")
		ResponseDto<List<BaseDto>> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + ApiPath.STATUS_DOMAIN, 
											HttpMethod.PUT, new HttpEntity<>(traineeDtos), ResponseDto.class).getBody();
		
		
		return responseDto.getStatusCode() == 200 ? true : false;
		
	}
	
	@Override
	public Boolean updateTopicMark (List<TraineeDto> traineeDtos) {
		
		
		@SuppressWarnings("unchecked")
		ResponseDto<List<BaseDto>> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + ApiPath.TOPICMARK_DOMAIN, 
											HttpMethod.PUT, new HttpEntity<>(traineeDtos), ResponseDto.class).getBody();
		
		
		return responseDto.getStatusCode() == 200 ? true : false;
		
	}
	
	@Override
	public Boolean updateRewardPenalty (List<TraineeDto> traineeDtos) {
		
		System.out.println(traineeDtos);
		@SuppressWarnings("unchecked")
		ResponseDto<List<BaseDto>> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.GET_ALL + ApiPath.REWARDPENALTY_DOMAIN, 
											HttpMethod.PUT, new HttpEntity<>(traineeDtos), ResponseDto.class).getBody();
		

		return responseDto.getStatusCode() == 200 ? true : false;
		
	}
	
	@Override
	public Boolean addTraineeToClass(List<Integer> traineeIds,Integer classId) {
		
		@SuppressWarnings("unchecked")
		ResponseDto<List<BaseDto>> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.ADD_TO_CLASS +"/" + classId, 
											HttpMethod.POST, new HttpEntity<>(traineeIds), ResponseDto.class).getBody();
		

		return responseDto.getStatusCode() == 200 ? true : false;
		
	}
	
	@Override
	public Boolean removeTraineesFromClass(List<Integer> traineeIds,Integer classId) {
		
		@SuppressWarnings("unchecked")
		ResponseDto<List<BaseDto>> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.DELETE_FROM_CLASS +"/" + classId, 
											HttpMethod.DELETE, new HttpEntity<>(traineeIds), ResponseDto.class).getBody();
		

		return responseDto.getStatusCode() == 200 ? true : false;
		
	}

	@Override
	public Boolean updateAttendentStatus(TraineeDtoList traineeDtoList) {
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.TRAINEE_DOMAIN + ApiPath.ATTENDENCE_STATUS_DOMAIN + ApiPath.UPDATE, 
											HttpMethod.PUT, new HttpEntity<TraineeDtoList>(traineeDtoList), ResponseDto.class).getBody();
		return responseDto.getStatusCode() == 200 ? true : false;
	}

}
