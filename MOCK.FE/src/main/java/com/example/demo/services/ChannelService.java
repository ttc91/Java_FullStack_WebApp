package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.ChannelDto;

public interface ChannelService {
	ChannelDto getOne(Integer id);

	Boolean update(ChannelDto dto);

	Boolean create(ChannelDto dto);

	Boolean delete(Integer id);

	List<ChannelDto> getAll();
}
