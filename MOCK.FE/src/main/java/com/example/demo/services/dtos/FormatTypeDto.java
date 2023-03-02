package com.example.demo.services.dtos;

import java.io.Serializable;

import com.example.demo.services.dtos.base.BaseDto;
import com.example.demo.services.dtos.enums.FormatTypeEnum;

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
public class FormatTypeDto extends BaseDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private FormatTypeEnum formatTypeName;
	
	private String remarks;

}
