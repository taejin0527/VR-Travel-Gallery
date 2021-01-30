package com.ssafy.iwc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.iwc.model.User;
import com.ssafy.iwc.model.request.LoginRequest;
import com.ssafy.iwc.model.request.SignupRequest;
import com.ssafy.iwc.model.response.JwtResponse;
import com.ssafy.iwc.model.response.MessageResponse;
import com.ssafy.iwc.security.jwt.JwtUtils;
import com.ssafy.iwc.security.services.UserDetailsImpl;
import com.ssafy.iwc.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserService userService;
	
	/**
	 * @author	������
	 * @desc 	ȸ������
	 */
	@ApiOperation(value="ȸ������ (�̸���, ��й�ȣ, �̸� ��), ��ȯ�� �׳� ���� �޼���")
	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if(userService.signUpUser(signUpRequest)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
	
	@ApiOperation(value = "���̵� �ߺ� üũ. �̹� �ִ� ���̵��� ��� fail�� ��ȯ�Ѵ�.", response = String.class)
	@GetMapping("/idcheck/{username}")
	public ResponseEntity<String> idCheck(@PathVariable String username) {
		System.out.println("controller " + username);
		if(userService.checkIdDuplication(username))
			return new ResponseEntity<String>("error", HttpStatus.OK);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}	
	
	@ApiOperation(value = "�̸��� �ߺ� üũ. �̹� �ִ� �̸����� ��� fail�� ��ȯ�Ѵ�.", response = String.class)
	@PostMapping(value = "/emailcheck")
	public ResponseEntity<String> edcheck(@RequestBody HashMap<String, String> userEmailData) {	
		if(userService.checkEmailDuplication(userEmailData.get("userEmail"))) 
			return new ResponseEntity<String>("error", HttpStatus.OK);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "���� �ڵ带 ������� �̸��Ϸ� ������. ������ ���۵Ǹ� ���� �ڵ带 ��ȯ�Ѵ�.", response = String.class)
	@PostMapping(value = "/emailvalidate")
	public ResponseEntity<String> createEmailCheck(@RequestBody HashMap<String, String> userEmailData) {
		String userEmail = userEmailData.get("userEmail");

		int random = new Random().nextInt(900000) + 100000;
		String authCode = String.valueOf(random);

		String subject = "ȸ������ ���� �ڵ� �߱� �ȳ� �Դϴ�.";
		StringBuilder sb = new StringBuilder();
		sb.append("������ ���� �ڵ�� " + authCode + "�Դϴ�.");

		if (userService.send(subject, sb.toString(), userEmail)) {
			return new ResponseEntity<String>(authCode, HttpStatus.OK); // vue���� authCode�� ��ġ ���� Ȯ�� �� �������� ��ư Ȱ��ȭ
		} else {
			return new ResponseEntity<String>("Error: ���� �̸��� ���� ����", HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * @author	������
	 * @desc 	�Ϲ� �α���
	 */
	@ApiOperation(value="(�̸���, ��й�ȣ)�� �α���, ������ jwt�� �⺻ ���� ��ȯ")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// ��û���� ���� ���̵�� ��й�ȣ�� ���� ������ ��ü ����
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		// JWT ����
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		// ��ȯ���� jwt�� (���� ���̵�, �̸�, �̸���)�� ������
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	

	/**
	 * @author	�赿��
	 * @desc 	�Ҽ� �α���(����, īī��)
	 */
	
	
	/**
	 * @author	�赿��
	 * @desc 	�α׾ƿ�
	 */
}