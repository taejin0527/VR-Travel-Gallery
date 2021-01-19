package com.ssafy.iwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.iwc.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
