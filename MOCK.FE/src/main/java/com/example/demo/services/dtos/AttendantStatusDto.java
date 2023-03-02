package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AttendantStatusDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String workingDay;

	private String status;
}
