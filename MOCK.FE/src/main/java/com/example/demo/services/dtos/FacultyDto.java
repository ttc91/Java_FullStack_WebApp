package com.example.demo.services.dtos;


import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
public class FacultyDto extends BaseDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer majorId;
	
	@NotEmpty(message = "Faculty name must not empty !!!")
	@Size(min = 5, max = 50, message = "Faculty name must be greater than 5 and lesser than 50")
	private String majorName; 
	
	@Size(max = 200)
	private String remarks;

	
}
