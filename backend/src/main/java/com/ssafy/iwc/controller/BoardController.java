package com.ssafy.iwc.controller;

import java.io.File;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.iwc.dto.BoardDto;
import com.ssafy.iwc.dto.PostImageDto;
import com.ssafy.iwc.service.BoardService;
import com.ssafy.iwc.service.PostImageService;
import com.ssafy.iwc.util.MD5Generator;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	private PostImageService postImageService;
	
	public BoardController(BoardService boardService, PostImageService postImageService) {
		this.boardService = boardService;
		this.postImageService = postImageService;
	}
//	@PostMapping("/requestupload")
//	public String requestUpload(MultipartHttpServletRequest mtfRequest) {
//		List<MultipartFile> fileList = mtfRequest.getFile("file");
//		String src = mtfRequest.getParameter("src");
//		System.out.println("src value : " + src);
//		
//		return "redirect:/";
//	}
	
	@PostMapping("/requestupload")
	public String write(@RequestParam("file") List<MultipartFile> files,BoardDto boardDto,@RequestParam("src") String src) {
		System.out.println(src);
		for(MultipartFile mf : files) {
			try {
				System.out.println(mf.toString());
				String origFilename = mf.getOriginalFilename();
				String filename = new MD5Generator(origFilename).toString();
//				실행되는 위치의 'files' 폴더에 파일이 저장
				String savePath = System.getProperty("user.dir")+"\\files";
//				파일이 저장되는 폴더가 없으면 폴더를 생성
				System.out.println("origFilename : "+origFilename);
				System.out.println("filename : "+filename);
				System.out.println("savePath : "+savePath);
				if(!new File(savePath).exists()) {
					try {
						new File(savePath).mkdir();
					}
					catch(Exception e) {
						e.getStackTrace();
					}
				}
				String filePath = savePath + "\\" + filename;
				mf.transferTo(new File(filePath));
				
				PostImageDto postImageDto = new PostImageDto();
				postImageDto.setOrigFilename(origFilename);
				postImageDto.setFilename(filename);
				postImageDto.setFilePath(filePath);
				
				int fileId = postImageService.saveFile(postImageDto);
//				boardDto.setId(fileId);
//				boardService.savePost(boardDto);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return "redirect:/";
		
	}
}
