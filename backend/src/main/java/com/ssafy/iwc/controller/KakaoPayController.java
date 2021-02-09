package com.ssafy.iwc.controller;

import java.io.IOException;
import java.net.MulticastSocket;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ssafy.iwc.dto.ChargeDto;
import com.ssafy.iwc.model.Amount;
import com.ssafy.iwc.model.Charge;
import com.ssafy.iwc.model.KakaoPay;
import com.ssafy.iwc.model.KakaoPayApproval;
import com.ssafy.iwc.service.ChargeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class KakaoPayController {

	private static final String HOST = "https://kapi.kakao.com";
	private KakaoPay kakaoPay;
	private KakaoPayApproval kakaoPayApproval;
	
	@Autowired
	ChargeService chargeService;
//	결제 요청
	@PostMapping("/kakao")
	public String kakao(@RequestParam("cost") String cost) {
		System.out.println("카카오 결제");
		
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("비용 : "+cost);
//		서버요청 헤더
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK "+"654e054049757a0cceb1fa50dffd3026");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
//		서버요청 바디
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
//		주문번호 DB테이블에서 가져오기
		params.add("partner_order_id", "1");
		params.add("partner_user_id", "ssafy");
		params.add("item_name", cost+"원");
//		비과세 금액보내야함
		params.add("tax_free_amount", "100");
//		수량
		params.add("quantity", "1");
//		금액
		params.add("total_amount", cost);
		params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");
		
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        System.out.println(body);
        try {
        	
        	kakaoPay = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPay.class);
            kakaoPay.setUser_id("ssafy");
            kakaoPay.setOrder_id("1");
            System.out.println(kakaoPay);
            
            return kakaoPay.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "/pay";
	}
//	결제 승인
	@GetMapping("/kakaoPaySuccess")
	public void kakaoPaySuccess(@RequestParam("pg_token")String pg_token, HttpServletResponse response) {
		
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("결제 완료");
		
//		서버로 요청할 header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK "+"654e054049757a0cceb1fa50dffd3026");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
//		서버요청 바디
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPay.getTid());
//		주문번호 DB테이블에서 가져오기
		
		params.add("partner_order_id", kakaoPay.getOrder_id());
		params.add("partner_user_id", kakaoPay.getUser_id());
		

	
		params.add("pg_token", pg_token);
		
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        System.out.println(body);
        try {
        	
        	kakaoPayApproval = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApproval.class);
//          DB에 저장
        	ChargeDto chargeDto = new ChargeDto();
        	chargeDto.setTid(kakaoPayApproval.getTid());
        	chargeDto.setPayday(kakaoPayApproval.getApproved_at());
        	chargeDto.setPrice(kakaoPayApproval.getAmount().getTotal());
        	chargeDto.setUsername(kakaoPayApproval.getPartner_user_id());
        	chargeService.save(chargeDto);
//        	사용자 돈 갱신
            
        	try {
				response.sendRedirect("http://localhost:8000/pay");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
//          페이지 이동
//            return "redirect:http://localhost:8000/pay";
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
//        return null;
		
	}
}