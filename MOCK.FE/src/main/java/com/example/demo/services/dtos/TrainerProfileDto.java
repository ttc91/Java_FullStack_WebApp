package com.example.demo.services.dtos;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

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
public class TrainerProfileDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fullName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String dateOfBirth;
	private Boolean gender;
	private String unit;
	private String major;
	private String phone;
	private String email;
	private String experience;
	private String remarks;
	
}
