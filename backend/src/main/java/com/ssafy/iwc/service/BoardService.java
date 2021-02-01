package com.ssafy.iwc.service;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.dto.BoardDto;

@Service
public interface BoardService {
	public long savePost(BoardDto boardDto);
	public BoardDto getPost(long id);
}
