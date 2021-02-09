package com.ssafy.iwc.service;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.model.User;
import com.ssafy.iwc.model.request.SignupRequest;

@Service("UserService")
public interface UserService {
	
	/**
	 * 새로운 유저 권한 설정
	 * @param User user - 사용자 정보(아이디, 이메일, 비번)
	 * @return boolean
	 */
	public boolean signUpUser(SignupRequest signUpRequest);
	
	/**
	 * 아이디 중복체크
	 * @param String username - 유저의 아이디
	 * @return boolean
	 */
	public boolean checkIdDuplication(String username);
	/**
	 * 이메일 중복체크
	 * @param String uemail - 유저의 이메일
	 * @return boolean
	 */
	public boolean checkEmailDuplication(String uemail);
	
	/**
	 * 인증번호 메일 발송
	 * @param String subject - 발신 메일 제목
	 * @param String text - 발신 메일 내용
	 * @param String to - 수신 메일 주소
	 * @return boolean
	 */

	public boolean send(String subject, String text, String to);


	public void updateUserMoney(Integer total,String userid);

}
