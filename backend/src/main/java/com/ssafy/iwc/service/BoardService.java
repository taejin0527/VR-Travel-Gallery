package com.ssafy.iwc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.dto.BoardDto;
import com.ssafy.iwc.model.Board;

@Service
public interface BoardService {
	public long savePost(BoardDto boardDto);
	public BoardDto getPost(long id);
	public void delPost(Long no);
	public List<Board> getLocationBoard(String location);
	public List<Board> getLocationIdxBoard(String location, int start, int idx);
}
