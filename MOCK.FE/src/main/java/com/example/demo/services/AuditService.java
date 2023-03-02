package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.AuditDto;

public interface AuditService {
	List<AuditDto> getAll();

	AuditDto getOne(Integer id);

	Boolean create(AuditDto dto);

	Boolean delete(Integer id);

	Boolean update(AuditDto dto, Integer id);
}
