package com.ssafy.iwc.service;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserDto getUserProfile(int uid) {
		UserDto user = new UserDto();
		
		return user;
	}

	@Override
	public String signIn(UserDto user) {
		return null;
	}

}
