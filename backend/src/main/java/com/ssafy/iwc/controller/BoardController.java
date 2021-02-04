package com.ssafy.iwc.controller;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.ssafy.iwc.dto.TagDto;
import com.ssafy.iwc.model.AllMainView;
import com.ssafy.iwc.model.AllView;
import com.ssafy.iwc.model.Board;
import com.ssafy.iwc.model.LocationInfo;
import com.ssafy.iwc.model.MainImage;
import com.ssafy.iwc.model.Tag;
import com.ssafy.iwc.service.BoardService;
import com.ssafy.iwc.service.MainImageService;
import com.ssafy.iwc.service.PostImageService;
import com.ssafy.iwc.service.PostImageServiceImpl;
import com.ssafy.iwc.service.TagService;
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
	@Autowired
	private TagService tagService;
	private String FileMainSrc = "http://i4d110.p.ssafy.io/apis/mainImg/";
	private String FileSubSrc = "http://i4d110.p.ssafy.io/apis/static/subImg/";
	
	@DeleteMapping("/delpost")
	public String delpost(@RequestParam("id") String id) {
		Long no = Long.parseLong(id);
		try {
//			이미지 정보 가져오기
			List<PostImageDto> del = postImageService.getFile(no);
			MainImageDto delMain = mainImageService.getFile(no);
//			파일삭제
			for(PostImageDto d : del) {
				File file = new File(d.getFilePath());
				if(file.exists()) {
					if(file.delete()) {
						System.out.println("서브사진 삭제");
					}else {
						System.out.println("사진삭제 실패");
					}
				}
			}
			File file = new File(delMain.getFilePath());
			if(file.exists()) {
				if(file.delete()) {
					System.out.println("메인사진 삭제");
				}else {
					System.out.println("사진삭제 실패");
				}
			}
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
	public ResponseEntity<List<AllMainView>> allview(@RequestParam("location") String location){
		System.out.println(location);
		try {
//			List<AllMainView> dto = mainImageService.getAllBoard();
			List<LocationInfo> result = new LinkedList<>();
			List<Board> dto = boardService.getLocationBoard(location);
			System.out.println(dto.get(0).toString());
//			List<Map<String, String>> result = new LinkedList<Map<String,String>>();
			System.out.println("Main Image : ");
			for(Board a : dto) {
				LocationInfo data = new LocationInfo();
//				data에 Board값 넣기
				data.setBoard(a);
//				메인 이미지 경로 가져와서 넣기
				Optional<MainImage> d = mainImageService.findById(a.getId());
				data.setFilePath(FileMainSrc+d.get().getFilename());
//				tag가져와서 넣기
				data.setTags(tagService.findTagId(a.getId()));
				System.out.println(data.getTags());

				result.add(data);
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
	public String write(@RequestParam("main") MultipartFile main, @RequestParam("file") List<MultipartFile> files,BoardDto boardDto,@RequestParam("writer") String writer,@RequestParam("location") String location,@RequestParam("nation") String nation,
			@RequestParam("tags") List<String> tags) {
		long id =0;
		
		try {
			//게시글 작성
			boardDto.setAuthor(writer);
			boardDto.setLocation(location);
			boardDto.setNation(nation);
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
		for(String s : tags) {
			try {
				if(s.equals(""))break;
				TagDto tagDto = new TagDto();
				
				tagDto.setNo(id);
				tagDto.setTag(s);
				tagService.saveFile(tagDto);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("서브이미지");
		System.out.println(files.size());
		for(MultipartFile mf : files) {
			try {
				
//				파일명 명명을 다시해야함 -> 해쉬값
				String origFilename = mf.getOriginalFilename();
				System.out.println(origFilename);
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
				
			} catch(Exception e) {
				e.printStackTrace();
				
			}
		}
		return "ok";
		
		
		
		
	}
}
