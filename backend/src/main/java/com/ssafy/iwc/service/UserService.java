package com.ssafy.iwc.service;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.dto.UserDto;

@Service
public interface UserService {
	public UserDto getUserProfile(int uid);
	public String signIn(UserDto user);
}
