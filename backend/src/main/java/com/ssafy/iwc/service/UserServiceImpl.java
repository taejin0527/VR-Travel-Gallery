package com.ssafy.iwc.service;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User getUserProfile(int uid) {
		User user = new User();
		
		return user;
	}

	@Override
	public String signIn(User user) {
		return null;
	}

}
