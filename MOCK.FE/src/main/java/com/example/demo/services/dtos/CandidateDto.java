package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.Date;

import com.example.demo.services.dtos.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CandidateDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Candidate Profile
	private CandidateProfileDto traineeCandidateProfile;
	
	// Candidate
	private Integer id;

	private String applicationDate;
	
	private ChannelDto channel;
//	
	private LocationDto location;

	private String note;

	private String status;
}
