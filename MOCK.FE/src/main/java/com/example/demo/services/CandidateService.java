package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.CandidateDto;

public interface CandidateService {
	CandidateDto getOne(Integer id);

	Boolean update(CandidateDto dto);

	Boolean create(CandidateDto dto);

	Boolean delete(Integer id);

	List<CandidateDto> getAll();
	
	List<CandidateDto> getAllPaging(Integer currentPage);

	Boolean deleteList(Integer[] ids);

	Boolean transferCandidate(CandidateDto dto);

}
