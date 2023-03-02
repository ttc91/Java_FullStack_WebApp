package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.example.demo.services.dtos.base.BaseDto;

import lombok.Data;

@Data
public class TraineeDto extends BaseDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer traineeId;
	private Boolean salaryPaid;
	private String tpBankAccount;
	private AllowanceGroupDto allowanceGroup;
	private Integer committedWorkingDuration;
	private String committedWorkingStartDate;
	private String remarks;
	private ProfileDto traineeCandidateProfile;
	private StatusDto status;
	private List<AllocationDto> allocations; 
	private List<RewardPenaltyDto> rewardPenalties;
	private List<TopicMarkDto> topicMarks;
	private List<AttendantStatusDto> attendantStatuses;
	private Integer clazzId;
}
