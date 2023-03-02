package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.TrainerDto;

public interface TrainerService {

	TrainerDto getOne(Integer id);

	Boolean update(TrainerDto dto);

	Boolean create(TrainerDto dto);

	Boolean delete(Integer id);

	List<TrainerDto> getAll();

	List<TrainerDto> getAllPaging(Integer currentPage);


}
