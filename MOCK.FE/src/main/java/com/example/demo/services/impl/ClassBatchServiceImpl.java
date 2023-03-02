package com.example.demo.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.config.ApiPath;
import com.example.demo.config.response.ResponseDto;
import com.example.demo.services.ClasssBatchService;
import com.example.demo.services.dtos.BudgetItemDto;
import com.example.demo.services.dtos.ClassBatchDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class ClassBatchServiceImpl implements ClasssBatchService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

	@Override
	public ClassBatchDto getOne(Integer id) {

		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CLASSBATCH_DOMAIN + ApiPath.GET_ONE + "/" + id,
						HttpMethod.GET, null, ResponseDto.class)
				.getBody();

		ClassBatchDto dto = mapper.map(responseDto.getObj(), ClassBatchDto.class);
		return dto;

	}

	@Override
	public Boolean update(ClassBatchDto dto, Integer id) {
		try {
			for (BudgetItemDto item : dto.getBudgetItems()) {
				item.setBudget(dto.getBudget());
			}
			return restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CLASSBATCH_DOMAIN + ApiPath.UPDATE + "/" + id,
					HttpMethod.PUT, new HttpEntity<>(dto), ResponseDto.class).getStatusCodeValue()!=200 ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Boolean updateStatus(ClassBatchDto dto, Integer id) {

		try {
			return restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CLASSBATCH_DOMAIN + ApiPath.UPDATE + "/edit_status/" + id,
					HttpMethod.PUT, new HttpEntity<>(dto), ResponseDto.class)
			.getStatusCodeValue() != 200 ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	@Override
	public Boolean create(ClassBatchDto dto) {

			try {
			for(BudgetItemDto item : dto.getBudgetItems()) {
				item.setBudget(dto.getBudget());
			}	
			return restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CLASSBATCH_DOMAIN + ApiPath.INSERT, HttpMethod.POST,
					new HttpEntity<>(dto), ResponseDto.class).getStatusCodeValue() != 201 ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Boolean delete(Integer id) {

		try {

			return restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CLASSBATCH_DOMAIN + ApiPath.DELETE + "/" + id,
					HttpMethod.DELETE, null, ResponseDto.class).getStatusCodeValue() != 200 ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<ClassBatchDto> getAll() {

		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CLASSBATCH_DOMAIN + ApiPath.GET_ALL, HttpMethod.GET, null,
						ResponseDto.class)
				.getBody();
		System.out.println(responseDto.getObjList());
		List<ClassBatchDto> dto = Arrays.asList(mapper.map(responseDto.getObjList(), ClassBatchDto[].class));
		return dto;

	}

	@Override
	public List<ClassBatchDto> getAllPaging(Integer currentPage) {
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CLASSBATCH_DOMAIN + ApiPath.GET_ALL + "/page/" + currentPage,
						HttpMethod.GET, null, ResponseDto.class)
				.getBody();
		List<ClassBatchDto> dto = Arrays.asList(mapper.map(responseDto.getObjList(), ClassBatchDto[].class));

		return dto;
	}

	@Override
	public List<ClassBatchDto> getFilter(String className, String locationName, String actualStartDate,
			String actualEndDate, String status) {
		String url = ApiPath.BE_LOCAL_HOST + ApiPath.CLASSBATCH_DOMAIN + ApiPath.GET_ALL + "/filter";
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		String urlTemplate = UriComponentsBuilder.fromHttpUrl(url).queryParam("className", "{className}")
				.queryParam("locationName", "{locationName}").queryParam("actualStartDate", "{actualStartDate}")
				.queryParam("actualEndDate", "{actualEndDate}").queryParam("status", "{status}").encode().toUriString();

		Map<String, String> params = new HashMap<>();
		params.put("className", className);
		params.put("locationName", locationName);
		params.put("actualStartDate", actualStartDate);
		params.put("actualEndDate", actualEndDate);
		params.put("status", status);

		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(urlTemplate, HttpMethod.GET, entity, ResponseDto.class, params).getBody();
		List<ClassBatchDto> dtoList = Arrays.asList(mapper.map(responseDto.getObjList(), ClassBatchDto[].class));
		return dtoList;

	}

}
