package com.example.demo.services.dtos;

import java.io.Serializable;

import com.example.demo.services.dtos.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfileDto extends BaseDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Integer profileId;
	private String account;
	private String fullName;
	private String gender;

	private String dateOfBirth;
	private String phone;
	private String email;
	private Integer universityPK;
	private Integer facultyPK;


}
