package com.ssafy.iwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.iwc.model.Like;
import com.ssafy.iwc.model.MultiId;

@Repository
public interface LikeRepository extends JpaRepository<Like, MultiId>{

	@Query(value = "select count(postsid) from likes where username = ? and postsid = ?",nativeQuery = true)
	int findLike(String username, long postsId);

}
