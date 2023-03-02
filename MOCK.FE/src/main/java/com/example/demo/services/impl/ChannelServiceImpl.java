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
import com.example.demo.services.ChannelService;
import com.example.demo.services.dtos.ChannelDto;
import com.example.demo.services.dtos.base.BaseDto;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

	@Override
	public ChannelDto getOne(Integer id) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CHANNEL_DOMAIN + ApiPath.GET_ONE + "/" + id, HttpMethod.GET,
						null, ResponseDto.class)
				.getBody();
		ChannelDto dto = mapper.map(responseDto.getObj(), ChannelDto.class);
		return dto;
	}

	@Override
	public Boolean update(ChannelDto dto) {
		// TODO Auto-generated method stub
		try {

			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CHANNEL_DOMAIN + ApiPath.UPDATE, HttpMethod.PUT,
					new HttpEntity<>(dto), ResponseDto.class).getBody();
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean create(ChannelDto dto) {
		// TODO Auto-generated method stub
		try {

			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CHANNEL_DOMAIN + ApiPath.INSERT, HttpMethod.POST,
					new HttpEntity<>(dto), ResponseDto.class).getBody();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		try {
			restTemplate.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CHANNEL_DOMAIN + ApiPath.DELETE + "/" + id,
					HttpMethod.DELETE, null, ResponseDto.class).getBody();

			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ChannelDto> getAll() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		ResponseDto<BaseDto> responseDto = restTemplate
				.exchange(ApiPath.BE_LOCAL_HOST + ApiPath.CHANNEL_DOMAIN + ApiPath.GET_ALL, HttpMethod.GET, null,
						ResponseDto.class)
				.getBody();

		List<ChannelDto> list = responseDto.getObjList().stream().map(obj -> mapper.map(obj, ChannelDto.class))
				.collect(Collectors.toList());

		return list;
	}

}
