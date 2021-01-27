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
import com.ssafy.iwc.dto.MainImageDto;
import com.ssafy.iwc.dto.PostImageDto;
import com.ssafy.iwc.service.BoardService;
import com.ssafy.iwc.service.MainImageService;
import com.ssafy.iwc.service.PostImageService;
import com.ssafy.iwc.util.MD5Generator;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	private PostImageService postImageService;
	private MainImageService mainImageService;
	
	public BoardController(BoardService boardService, PostImageService postImageService) {
		this.boardService = boardService;
		this.postImageService = postImageService;
	}
	@PostMapping("/getposts")
	public String getpost() {
		System.out.println("����");
		List<PostImageDto> dto = postImageService.getFile(38);
		System.out.println(dto.size());
		
		return "redirect:/";
	}
	
	@PostMapping("/requestupload")
	public String write(@RequestParam("main") MultipartFile main, @RequestParam("file") List<MultipartFile> files,BoardDto boardDto,@RequestParam("writer") String writer,@RequestParam("location") String location) {
		long id =0;
		try {
			//�Խñ� �ۼ�
			boardDto.setAuthor(writer);
			boardDto.setLocation(location);
			id = boardService.savePost(boardDto);
			//���� �̹��� �ۼ�
			String origname = main.getOriginalFilename();
			String fname = new MD5Generator(origname).toString();
//			����Ǵ� ��ġ�� 'files' ������ ������ ����
			String sPath = System.getProperty("user.dir")+"\\mains";
//			������ ����Ǵ� ������ ������ ������ ����

			if(!new File(sPath).exists()) {
				try {
					new File(sPath).mkdir();
				}
				catch(Exception e) {
					e.getStackTrace();
				}
			}
			String fPath = sPath + "\\" + fname;
			main.transferTo(new File(fPath));
	
			MainImageDto mainImageDto = new MainImageDto();
			mainImageDto.setId(id);
			mainImageDto.setOrigFilename(origname);
			mainImageDto.setFilename(fname);
			mainImageDto.setFilePath(fPath);
			
			
			mainImageService.saveFile(mainImageDto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		for(MultipartFile mf : files) {
			try {
			
				
				String origFilename = mf.getOriginalFilename();
				String filename = new MD5Generator(origFilename).toString();
//				����Ǵ� ��ġ�� 'files' ������ ������ ����
				String savePath = System.getProperty("user.dir")+"\\files";
//				������ ����Ǵ� ������ ������ ������ ����

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
				postImageDto.setId(id);
				postImageDto.setOrigFilename(origFilename);
				postImageDto.setFilename(filename);
				postImageDto.setFilePath(filePath);
				
				postImageService.saveFile(postImageDto);

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return "redirect:/";
		
	}
}
