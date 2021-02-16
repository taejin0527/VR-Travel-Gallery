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
import org.springframework.web.bind.annotation.RequestParam;
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
	 * @author	김태진
	 * @desc 	회원가입
	 */
	@ApiOperation(value="회원가입 (이메일, 비밀번호, 이름 등), 반환은 그냥 성공 메세지")
	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if(userService.signUpUser(signUpRequest)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
	
	@ApiOperation(value = "아이디 중복 체크. 이미 있는 아이디인 경우 fail을 반환한다.", response = String.class)
	@GetMapping("/idcheck/{username}")
	public ResponseEntity<String> idCheck(@PathVariable String username) {
		System.out.println("controller " + username);
		if(userService.checkIdDuplication(username))
			return new ResponseEntity<String>("error", HttpStatus.OK);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}	
	
	@ApiOperation(value = "이메일 중복 체크. 이미 있는 이메일인 경우 fail을 반환한다.", response = String.class)
	@PostMapping(value = "/emailcheck")
	public ResponseEntity<String> edcheck(@RequestBody HashMap<String, String> userEmailData) {	
		if(userService.checkEmailDuplication(userEmailData.get("userEmail"))) 
			return new ResponseEntity<String>("error", HttpStatus.OK);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "인증 코드를 사용자의 이메일로 보내기. 메일이 전송되면 인증 코드를 반환한다.", response = String.class)
	@PostMapping(value = "/emailvalidate")
	public ResponseEntity<String> createEmailCheck(@RequestBody HashMap<String, String> userEmailData) {
		String userEmail = userEmailData.get("userEmail");

		int random = new Random().nextInt(900000) + 100000;
		String authCode = String.valueOf(random);

		String subject = "회원가입 인증 코드 발급 안내 입니다.";
		StringBuilder sb = new StringBuilder();
		sb.append("귀하의 인증 코드는 " + authCode + "입니다.");

		if (userService.send(subject, sb.toString(), userEmail)) {
			return new ResponseEntity<String>(authCode, HttpStatus.OK); // vue에서 authCode로 일치 여부 확인 후 계정생성 버튼 활성화
		} else {
			return new ResponseEntity<String>("Error: 인증 이메일 전송 실패", HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * @author	김태진
	 * @desc 	일반 로그인
	 */
	@ApiOperation(value="(이메일, 비밀번호)로 로그인, 성공시 jwt와 기본 정보 반환")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// 요청으로 받은 아이디와 비밀번호를 통해 인증용 객체 생성
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		// JWT 생성
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		// 반환으로 jwt와 (고유 아이디, 이름, 이메일)을 보낸다
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	

	/**
	 * @author	김동걸
	 * @desc 	소셜 로그인(구글, 카카오)
	 */
	
	@ApiOperation(value="유저명을 통해 유저정보를 가져옴")
	@GetMapping("/getuser")
	public ResponseEntity getuser(@RequestParam("username")String username) {
		try {
			User user = userService.getUserInfo(username);
			return new ResponseEntity(user,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
		}
		
		
		
		
	}
	
	
	/**
	 * @author	김동걸
	 * @desc 	로그아웃
	 */
}
	
