package com.ssafy.iwc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.iwc.repository.PayInfoRepository;

@Service
public class PayInfoServiceImpl implements PayInfoService{

	@Autowired
	PayInfoRepository payInfoRepository;
	
	@Override
	public int getPayRequest(String username, long no) {
		// TODO Auto-generated method stub
		return payInfoRepository.getgetPayRequest(username,no);
	}

	@Override
	public void saveInfo(long no, String username) {
		// TODO Auto-generated method stub
		
	}

}
