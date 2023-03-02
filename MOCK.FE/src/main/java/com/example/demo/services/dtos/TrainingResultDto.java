package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.services.dtos.base.BaseDto;

import lombok.Data;

@Data
public class TrainingResultDto extends BaseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer traineeId;
	private Integer classId;
	private List<MilestoneDto> milestoneDtos = new ArrayList<MilestoneDto>();
	private List<AttendanceStatusDto> attendanceStatusDtos = new ArrayList<AttendanceStatusDto>();
	
	private List<MilestoneMarkDto> milestoneMarkDtos = new ArrayList<MilestoneMarkDto>();
	private List<TopicMarkDto> topicMarkDtos = new ArrayList<TopicMarkDto>();	//use when update
	
	private List<RewardPenaltyDto> rewardPenaltyDtos = new ArrayList<RewardPenaltyDto>();
	private List<GPADto> gpaDtos = new ArrayList<GPADto>();
	private CommitmentDto commitmentDto;
	private List<AllowanceDto> allowanceDtos = new ArrayList<AllowanceDto>();
	private List<AllocationDto> allocationDtos;
	
	
}
