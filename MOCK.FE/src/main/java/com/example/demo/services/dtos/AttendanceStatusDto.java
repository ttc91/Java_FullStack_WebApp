package com.example.demo.services.dtos;

import lombok.Data;

@Data
public class AttendanceStatusDto {
	private Integer milestoneId;
	private String milestoneName;
	private int absentTimes;
	private int lateAndEarlyLeave;
	private float noPermissionRate;
	private float discipinaryPoint;
}
