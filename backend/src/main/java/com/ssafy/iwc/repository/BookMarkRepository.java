package com.ssafy.iwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.iwc.model.BookMark;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, String>{
	@Query(value = "select count(username) from bookmark where username = ? and targetname = ?",nativeQuery = true)
	int findCheck(String username, String targetname);

}
