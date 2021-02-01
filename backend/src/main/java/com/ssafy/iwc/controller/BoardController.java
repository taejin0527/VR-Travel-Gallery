package com.ssafy.iwc.controller;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.iwc.dto.BoardDto;
import com.ssafy.iwc.dto.MainImageDto;
import com.ssafy.iwc.dto.PostImageDto;
import com.ssafy.iwc.model.AllMainView;
import com.ssafy.iwc.model.AllView;
import com.ssafy.iwc.service.BoardService;
import com.ssafy.iwc.service.MainImageService;
import com.ssafy.iwc.service.PostImageService;
import com.ssafy.iwc.service.PostImageServiceImpl;
import com.ssafy.iwc.util.MD5Generator;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private PostImageService postImageService;
	@Autowired
	private MainImageService mainImageService;
	private String FileMainSrc = "http://localhost:8080/static/mainImg/";
	private String FileSubSrc = "http://localhost:8080/static/subImg/";
	
	@DeleteMapping("/delpost")
	public String delpost(@RequestParam("id") String id) {
		Long no = Long.parseLong(id);
		try {
//			이미지 정보 가져오기
//			파일삭제
//			DB삭제
			postImageService.delPost(no);
			mainImageService.delPost(no);
			boardService.delPost(no);
			return "OK";
		}catch(Exception e) {
			System.out.println("에러");
			return "FALSE";
		}
		
	}
	
	@GetMapping("/allview")
	public ResponseEntity<List<AllMainView>> allview(){
		
		try {
			List<AllMainView> dto = mainImageService.getAllBoard();
			List<Map<String, String>> result = new LinkedList<Map<String,String>>();
			System.out.println("Main Image : ");
			for(AllMainView a : dto) {
				Map<String,String> m = new HashMap<String, String>();
				System.out.println("아이디 "+a.getId());
				System.out.println("작성자 : " +a.getAuthor());
				System.out.println("파일 : " +a.getFilename());
				m.put("id",Long.toString(a.getId()));
				m.put("author",a.getAuthor());
				m.put("filepath",FileMainSrc+a.getFilename());
				result.add(m);
			}
			return new ResponseEntity(result,HttpStatus.OK);
		}catch(Exception e){
			System.out.println("에러");
			return new ResponseEntity(e,HttpStatus.FAILED_DEPENDENCY);

		}
		
		
		
	}
	
	
	
	@GetMapping("/getposts")
	public ResponseEntity<List<AllView>> getpost(@RequestParam("id") String id) {
		long no = Long.parseLong(id);
		
		
		try {
			List<AllView> dto = postImageService.findSubImg(no);
			List<Map<String, String>> result = new LinkedList<Map<String,String>>();
			

			
			for(AllView a : dto) {
				Map<String,String> m = new HashMap<String, String>();
				System.out.println("아이디 "+a.getId());
				System.out.println("작성자 : " +a.getAuthor());
				System.out.println("파일 : " +a.getFilename());
				m.put("id",Long.toString(a.getId()));
				m.put("author",a.getAuthor());
				m.put("filepath",FileSubSrc+a.getFilename());
				result.add(m);
			}
			AllMainView all = mainImageService.findMainImg(no);
			Map<String,String> m = new HashMap<String, String>();
			
			m.put("id",Long.toString(all.getId()));
			m.put("author",all.getAuthor());
			m.put("filepath",FileMainSrc+all.getFilename());
			result.add(m);
			
			return new ResponseEntity(result, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(e,HttpStatus.FAILED_DEPENDENCY);
		}

		

		
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
			String expend="";
			for(int i=0;i<origname.length();i++) {
				if(origname.charAt(i)=='.') {
					expend = origname.substring(i,origname.length());
					break;
				}
			}
			String fname = new MD5Generator(origname).toString()+expend;
//			실행되는 위치의 'files' 폴더에 저장
			String sPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\mainImg";
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
				String expend="";
				for(int i=0;i<origFilename.length();i++) {
					if(origFilename.charAt(i)=='.') {
						expend = origFilename.substring(i,origFilename.length());
						break;
					}
				}
				String filename = new MD5Generator(origFilename).toString()+expend;
//				실행되는 위치의 'files' 폴더에 파일이 저장
				String savePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\subImg";
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
				return "OK";
			} catch(Exception e) {
				e.printStackTrace();
				
			}
		}
		return "false";
		
		
		
		
	}
}
