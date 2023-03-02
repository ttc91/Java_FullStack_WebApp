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
public class CandidateProfileDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Candidate Profile
	private Integer profileId;
	
	private String account;

	private String fullName;

	private String dateOfBirth;

	private String gender;

	private UniversityDto university;
//
	private FacultyDto faculty;

	private String phone;

	private String email;

	private String cv;

	private String skill;

	private String graduationYear;

	private String foreignLanguage;

	private Integer level;

	private String type;

}
