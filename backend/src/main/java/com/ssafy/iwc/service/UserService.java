package com.ssafy.iwc.service;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.model.User;

@Service
public interface UserService {
	public User getUserProfile(int uid);
	public String signIn(User user);
}
