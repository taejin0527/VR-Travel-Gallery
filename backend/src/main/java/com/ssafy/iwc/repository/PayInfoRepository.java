package com.ssafy.iwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.iwc.model.PayInfo;

@Repository
public interface PayInfoRepository extends JpaRepository<PayInfo, Long>{

	
	
	@Query(value = "select count(id) from payinfo where username = ? and postid = ?",nativeQuery = true)
	int getgetPayRequest(String username, long no);

}
