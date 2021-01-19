package com.ssafy.iwc.service;

import javax.transaction.Transactional;

import com.ssafy.iwc.dto.BoardDto;
import com.ssafy.iwc.model.Board;
import com.ssafy.iwc.repository.BoardRepository;

public class BoardService {

	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	  
	@Transactional public int savePost(BoardDto boardDto) { 
		return boardRepository.save(boardDto.toEntity()).getId(); 
	}
	 

	@Transactional
	public BoardDto getPost(int id) {
		Board board = boardRepository.findById(id).get();

		BoardDto boardDto = BoardDto.builder().id(board.getId()).author(board.getAuthor()).content(board.getContent())
				.postImageId(board.getPostImageId()).createdDate(board.getCreatedDate()).build();
		return boardDto;

	}
}
