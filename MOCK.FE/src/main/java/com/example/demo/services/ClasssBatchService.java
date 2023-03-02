package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.ClassBatchDto;
public interface ClasssBatchService {

	List<ClassBatchDto> getAll();

	ClassBatchDto getOne(Integer id);

	Boolean delete(Integer id);

	Boolean update(ClassBatchDto dto, Integer id);

	List<ClassBatchDto> getAllPaging(Integer currentPage);

	Boolean updateStatus(ClassBatchDto dto, Integer id);

	List<ClassBatchDto> getFilter(String className, String locationName, String actualStartDate, String actualEndDate,
			String status);

	Boolean create(ClassBatchDto dto);

}
