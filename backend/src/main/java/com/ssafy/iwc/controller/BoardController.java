package com.ssafy.iwc.controller;

import java.io.File;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.iwc.dto.BoardDto;
import com.ssafy.iwc.dto.PostImageDto;
import com.ssafy.iwc.service.BoardService;
import com.ssafy.iwc.service.PostImageService;
import com.ssafy.iwc.util.MD5Generator;

public class BoardController {
	private BoardService boardService;
	private PostImageService postImageService;
	
	public BoardController(BoardService boardService, PostImageService postImageService) {
		this.boardService = boardService;
		this.postImageService = postImageService;
	}
	
	
	@PostMapping("/post")
	public String write(@RequestParam("file") MultipartFile files,BoardDto boardDto) {
		try {
			String origFilename = files.getOriginalFilename();
			String filename = new MD5Generator(origFilename).toString();
//			실행되는 위치의 'files' 폴더에 파일이 저장
			String savePath = System.getProperty("user.dir")+"\\files";
//			파일이 저장되는 폴더가 없으면 폴더를 생성
			if(!new File(savePath).exists()) {
				try {
					new File(savePath).mkdir();
				}
				catch(Exception e) {
					e.getStackTrace();
				}
			}
			String filePath = savePath + "\\" + filename;
			files.transferTo(new File(filePath));
			
			PostImageDto postImageDto = new PostImageDto();
			postImageDto.setOrigFilename(origFilename);
			postImageDto.setFilename(filename);
			postImageDto.setFilePath(filePath);
			
			int fileId = postImageService.saveFile(postImageDto);
			boardDto.setId(fileId);
			boardService.savePost(boardDto);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/";
		
	}
}
