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
@ToString
@Getter
@Setter
public class AllowanceGroupDto extends BaseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer allowanceGroupId;
	
	@NotEmpty(message = "Allowance group name must not empty !!!")
	@Size(min = 5, max = 50, message = "Allowance group name must be greater than 5 and lesser than 50")
	private String name;
	
	@Size(max = 200)
	private String remarks;
		
}
