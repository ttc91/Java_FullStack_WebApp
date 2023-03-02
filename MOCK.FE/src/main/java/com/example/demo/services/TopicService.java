package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.TopicDto;

public interface TopicService {

	List<TopicDto> getAll();

	List<TopicDto> getTopicByClassId(Integer classId);

}
