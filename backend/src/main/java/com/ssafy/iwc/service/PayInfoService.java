package com.ssafy.iwc.service;

import org.springframework.stereotype.Service;

@Service
public interface PayInfoService {

	int getPayRequest(String username, long no);

	void saveInfo(long no, String username);

}
