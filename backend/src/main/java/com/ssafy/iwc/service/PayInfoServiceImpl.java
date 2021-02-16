package com.ssafy.iwc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.iwc.dto.PayInfoDto;
import com.ssafy.iwc.model.User;
import com.ssafy.iwc.repository.BoardRepository;
import com.ssafy.iwc.repository.PayInfoRepository;
import com.ssafy.iwc.repository.UserRepository;

@Service
public class PayInfoServiceImpl implements PayInfoService{

	@Autowired
	PayInfoRepository payInfoRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public int getPayRequest(String username, long no) {
		// TODO Auto-generated method stub
		return payInfoRepository.getgetPayRequest(username,no);
	}

	@Transactional
	public boolean saveInfo(PayInfoDto payInfoDto) {
		// TODO Auto-generated method stub
		try {
//			결제 진행
			User user = userRepository.findByUsername(payInfoDto.getUsername()).get();
			userRepository.upDateMoney(user.getMoney()-3,payInfoDto.getUsername());
//			결제정보 저장
			payInfoRepository.save(payInfoDto.toEntity());
		
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
		return true;
	}

	

}
