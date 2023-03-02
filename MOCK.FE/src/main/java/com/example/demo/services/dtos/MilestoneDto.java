package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MilestoneDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer milestoneId;
	private String milestoneName;
	private Double totalMaxScore; 	
	private Double totalWeightedNumber;
	private Double totalPassingScore; 	
	private String startDate;
	private String endDate;
	private Boolean salaryPaid;
	private List<TopicDto> topics = new ArrayList<TopicDto>();
	private String willDelete;
}
