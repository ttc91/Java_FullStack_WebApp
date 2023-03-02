package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.List;

import com.example.demo.services.dtos.base.BaseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TraineeDtoList extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Integer classId;
	
	Integer milestoneId;
	
	List<TraineeDto> traineeDtos;




	public TraineeDtoList(List<TraineeDto> traineeDtos) {
		super();
		this.traineeDtos = traineeDtos;
	}
		
}
