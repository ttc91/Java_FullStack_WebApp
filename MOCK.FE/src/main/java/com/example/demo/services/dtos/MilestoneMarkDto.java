package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;

@Data
public class MilestoneMarkDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String milestoneName;
	private Double milestoneMark;
	private List<TopicMarkDto> topicMarkDtos = new ArrayList<TopicMarkDto>(); 
}
