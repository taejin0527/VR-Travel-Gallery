package com.ssafy.iwc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.iwc.dto.BoardDto;
import com.ssafy.iwc.model.Board;
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
//	@Query(value = "select * from posts where location=?",nativeQuery = true)
	List<Board> findByLocation(String location);

	
	@Query(value = "select * from posts where location = ? order by id desc limit ?,?",nativeQuery = true)
	List<Board> getLocationIdxBoard(String location, int start, int idx);
}
