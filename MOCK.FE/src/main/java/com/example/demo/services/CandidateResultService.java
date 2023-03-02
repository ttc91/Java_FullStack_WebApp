package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.CandidateResultsDto;

public interface CandidateResultService {
	CandidateResultsDto getByID(Integer id);
	Boolean update(CandidateResultsDto candidateResultsDto, Integer id); 

}
