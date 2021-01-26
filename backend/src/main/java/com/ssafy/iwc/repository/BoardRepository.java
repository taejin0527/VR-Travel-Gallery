package com.ssafy.iwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.iwc.model.Board;
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
