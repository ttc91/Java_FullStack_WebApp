package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.List;

import com.example.demo.services.dtos.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResultsDto extends BaseDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private List<EntryTestDto> entryTests;
	private List<InterviewDto> interviews;

}
