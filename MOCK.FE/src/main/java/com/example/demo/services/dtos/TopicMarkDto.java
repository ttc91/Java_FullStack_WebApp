package com.example.demo.services.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class TopicMarkDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer topicId;
	private String topicName;
	private Double score;
}
