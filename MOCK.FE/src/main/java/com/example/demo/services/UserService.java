package com.example.demo.services;

import com.example.demo.services.dtos.UserDto;
import com.example.demo.services.dtos.base.BaseDto;

public interface UserService {

	UserDto login(BaseDto dto);

	UserDto findByUsername(String username);

}
