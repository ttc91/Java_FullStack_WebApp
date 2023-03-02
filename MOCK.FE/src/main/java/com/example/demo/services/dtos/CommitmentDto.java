package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CommitmentDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double commitedValue;
	private Integer commitedWorkingDuration;
	private String commitedWorkingStartDate;
	private String commitedWorkingEndDate;
	private String remarks;
}
