package com.ssafy.iwc.controller;

import java.io.File;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mysql.cj.xdevapi.JsonArray;
import com.ssafy.iwc.dto.BoardDto;
import com.ssafy.iwc.dto.MainImageDto;
import com.ssafy.iwc.dto.PostImageDto;
import com.ssafy.iwc.model.AllView;
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
	
	public BoardController(BoardService boardService, PostImageService postImageService,MainImageService mainImageService) {
		this.boardService = boardService;
		this.postImageService = postImageService;
		this.mainImageService = mainImageService;
	}
	@GetMapping("allview")
	public void allview(){
		try {
			List<AllView> dto = mainImageService.getAllBoard();
			for(int i=0;i<dto.size();i++) {
				System.out.println(dto.get(i).toString());
			}
//			return new ResponseEntity(dto,HttpStatus.OK);
		}catch(Exception e){
			System.out.println("에러");
//			return new ResponseEntity(e,HttpStatus.FAILED_DEPENDENCY);
//			ㄴㄴ
		}
		
		
		
	}
	
	
	
	@GetMapping("/getposts")
	public ResponseEntity<List<PostImageDto>> getpost() {
		System.out.println("");
//		MainImageDto mainImageDto = mainImageService.getFile(45);
//		
//		model.addAttribute("files", mainImageDto);
		
//		System.out.println(mainImageDto.toString());
		try {
			List<PostImageDto> dto = postImageService.getFile(38);
			for(PostImageDto d : dto) {
				d.setFilePath("http://localhost:8080/static/files/"+d.getFilename()+".jpg");
			}
			return new ResponseEntity(dto,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(e,HttpStatus.FAILED_DEPENDENCY);
		}
		
//		System.out.println(mainImageDto.);
		

		
	}
	
	@PostMapping("/requestupload")
	public String write(@RequestParam("main") MultipartFile main, @RequestParam("file") List<MultipartFile> files,BoardDto boardDto,@RequestParam("writer") String writer,@RequestParam("location") String location) {
		long id =0;
		try {
			//게시글 작성
			boardDto.setAuthor(writer);
			boardDto.setLocation(location);
			id = boardService.savePost(boardDto);
			//메인 이미지 작성
			String origname = main.getOriginalFilename();
			String fname = new MD5Generator(origname).toString();
//			실행되는 위치의 'files' 폴더에 저장
			String sPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";
//			파일이 저장되는 폴더가 없으면 폴더를 생성
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
			
//				파일명 명명을 다시해야함 -> 해쉬값
				String origFilename = mf.getOriginalFilename();
				String filename = new MD5Generator(origFilename).toString();
//				실행되는 위치의 'files' 폴더에 파일이 저장
				String savePath = System.getProperty("user.dir")+"\\files";
//				파일이 저장되는 폴더가 없으면 폴더를 생성	

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
