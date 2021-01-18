package com.ssafy.iwc.services;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.models.UserDto;

@Service
public interface UserService {
	public UserDto getUserProfile(int uid);
	public String signIn(UserDto user);
}
