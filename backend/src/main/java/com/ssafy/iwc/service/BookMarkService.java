package com.ssafy.iwc.service;

import org.springframework.stereotype.Service;

@Service
public interface BookMarkService {

	int findCheck(String username, String targetname);

	boolean delete(String username, String targetname);

	boolean add(String username, String targetname);

}
