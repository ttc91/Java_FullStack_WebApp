package com.example.demo.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ApiPath;
import com.example.demo.config.response.ResponseDto;
import com.example.demo.services.CandidateResultService;
import com.example.demo.services.dtos.CandidateResultsDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class CandidateResultServiceImpl implements CandidateResultService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

	@Override
	public CandidateResultsDto getByID(Integer id) {
		// TODO Auto-generated method stub
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CANDIDATE_RESULT_DOMAIN + ApiPath.GET_ONE + "/" + id,
						HttpMethod.GET, null, ResponseDto.class)
				.getBody();
		// System.out.println(responseDto);
		CandidateResultsDto candidateResultsDto = mapper.map(responseDto.getObj(), CandidateResultsDto.class);
		System.out.println(responseDto);
		return candidateResultsDto;
	}

	@Override
	public Boolean update(CandidateResultsDto dto, Integer id) {
		// TODO Auto-generated method stub
		try {
			ResponseDto<BaseDto> responseDto = restTemplate
					.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CANDIDATE_RESULT_DOMAIN + ApiPath.INSERT + "/" + id,
							HttpMethod.POST, new HttpEntity<>(dto), ResponseDto.class)
					.getBody();
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
