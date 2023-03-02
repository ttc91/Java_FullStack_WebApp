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
public class LocationDto extends BaseDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer locationId;
	
	private String locationName; 
	
	private String remarks;

	
}
