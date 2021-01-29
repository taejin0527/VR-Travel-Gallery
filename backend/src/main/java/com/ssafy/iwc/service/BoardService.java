package com.ssafy.iwc.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.dto.BoardDto;
import com.ssafy.iwc.model.Board;
import com.ssafy.iwc.repository.BoardRepository;
@Service
public class BoardService {

	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	  
	@Transactional public long savePost(BoardDto boardDto) { 
		return boardRepository.save(boardDto.toEntity()).getId(); 
	}
	 

	@Transactional
	public BoardDto getPost(long id) {
		Board board = boardRepository.findById(id).get();

		BoardDto boardDto = BoardDto.builder()
				.id(id)
				.author(board.getAuthor())
				.good(board.getGood())
				.views(board.getViews())
				.location(board.getLocation())
				.createdDate(board.getCreatedDate())
				.build();
		return boardDto;

	}
}
