package com.ssafy.iwc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.iwc.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="UserController", tags="���� ��Ʈ�ѷ�")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@ApiOperation(value="ȸ�� ������ ��ȯ", response = User.class)
	@GetMapping("/profile")
	public ResponseEntity getUserProfile() {
		return null;
	}
}
