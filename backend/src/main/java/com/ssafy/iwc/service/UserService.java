package com.ssafy.iwc.service;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.model.User;
import com.ssafy.iwc.model.request.SignupRequest;

@Service("UserService")
public interface UserService {
	
	/**
	 * ���ο� ���� ���� ����
	 * @param User user - ����� ����(���̵�, �̸���, ���)
	 * @return boolean
	 */
	public boolean signUpUser(SignupRequest signUpRequest);
	
	/**
	 * ���̵� �ߺ�üũ
	 * @param String username - ������ ���̵�
	 * @return boolean
	 */
	public boolean checkIdDuplication(String username);
	/**
	 * �̸��� �ߺ�üũ
	 * @param String uemail - ������ �̸���
	 * @return boolean
	 */
	public boolean checkEmailDuplication(String uemail);
	
	/**
	 * ������ȣ ���� �߼�
	 * @param String subject - �߽� ���� ����
	 * @param String text - �߽� ���� ����
	 * @param String to - ���� ���� �ּ�
	 * @return boolean
	 */
	public boolean send(String subject, String text, String to);
}
