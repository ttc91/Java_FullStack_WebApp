package com.example.demo.services.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class AllocationDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String allocatedFSU;
	private Double salary;
	private String startDate;
	private String allocationStatus;
	private String remarks;
}
