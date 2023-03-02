package com.example.demo.services;

import java.util.List;

import com.example.demo.services.dtos.ClassAdminDto;

public interface AdminService {
	List<ClassAdminDto> getAll();
}
