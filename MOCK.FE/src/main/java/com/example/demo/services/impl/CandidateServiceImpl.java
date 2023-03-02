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
import com.example.demo.services.CandidateService;
import com.example.demo.services.dtos.CandidateDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

	@Override
	public CandidateDto getOne(Integer id) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CANDIDATE_DOMAIN + ApiPath.GET_ONE + "/" + id, HttpMethod.GET,
						null, ResponseDto.class)
				.getBody();
		// System.out.println(responseDto);
		CandidateDto candidateDto = mapper.map(responseDto.getObj(), CandidateDto.class);
		return candidateDto;
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		try {	
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CANDIDATE_DOMAIN + ApiPath.DELETE + "/" + id, 
											HttpMethod.DELETE, null, ResponseDto.class).getBody();
			
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Boolean deleteList(Integer[] ids) {
		try {
			for(int i = 0 ; i<ids.length; i++) {
				ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CANDIDATE_DOMAIN + ApiPath.DELETE + "/" + ids[i], 
						HttpMethod.DELETE, null, ResponseDto.class).getBody();
				if(responseDto.getStatusCode()!=200) {
					return false;
				}
			}
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}



	@Override
	public List<CandidateDto> getAll() {
		// TODO Auto-generated method stub
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CANDIDATE_DOMAIN + ApiPath.GET_ALL, HttpMethod.GET, null,
						ResponseDto.class)
				.getBody();

		List<CandidateDto> candidateDtos = Arrays.asList(mapper.map(responseDto.getObjList(), CandidateDto[].class));
		return candidateDtos;
	}

	@Override
	public List<CandidateDto> getAllPaging(Integer currentPage) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CANDIDATE_DOMAIN + ApiPath.GET_ALL + "/page/" + currentPage,
						HttpMethod.GET, null, ResponseDto.class)
				.getBody();
		System.out.println(responseDto.toString());

		List<CandidateDto> dto = Arrays.asList(mapper.map(responseDto.getObjList(), CandidateDto[].class));
		System.out.println(dto.toString());
		return dto;
	}

	@Override
	public Boolean update(CandidateDto dto) {
		// TODO Auto-generated method stub
		try {
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CANDIDATE_DOMAIN + ApiPath.UPDATE + "/" + dto.getId(),
					HttpMethod.PUT, new HttpEntity<>(dto), ResponseDto.class).getBody();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean create(CandidateDto dto) {
		// TODO Auto-generated method stub
		try {
			ResponseDto<BaseDto> responseDto = restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CANDIDATE_DOMAIN + ApiPath.INSERT, HttpMethod.POST,
					new HttpEntity<>(dto), ResponseDto.class).getBody();
			if(responseDto.getStatusCode()==201){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean transferCandidate(CandidateDto dto) {
		// TODO Auto-generated method stub
		try {
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CANDIDATE_DOMAIN + ApiPath.TRANSFER, HttpMethod.POST,
					new HttpEntity<>(dto), ResponseDto.class).getBody();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
