package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.services.UserService;
import com.example.demo.services.dtos.UserDto;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserDto user = service.findByUsername(username);
		
		if(user == null) {
			System.out.println("===user null");
			throw new UsernameNotFoundException("User not found !!!");
		}
		
		return new MyUserDetails(user);
	}
	
}
