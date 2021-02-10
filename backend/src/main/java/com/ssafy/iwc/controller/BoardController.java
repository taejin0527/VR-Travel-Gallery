package com.ssafy.iwc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
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

import com.jcraft.jsch.Session;
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
import com.ssafy.iwc.util.SFTPsender;

import io.swagger.annotations.ApiOperation;

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
//	이부분 처리
	private String FileMainSrc = "https://i4d110.p.ssafy.io/images/mainImg/";
	private String FileSubSrc = "https://i4d110.p.ssafy.io/images/subImg/";
	
	
	@ApiOperation(value = "게시물 id를 보내면 게시물과 관련 모든것 삭제", response = String.class)
	@DeleteMapping("/delpost")
	public String delpost(@RequestParam("id") String id) {
		Long no = Long.parseLong(id);
		try {
//			이미지 정보 가져오기
			List<PostImageDto> del = postImageService.getFile(no);
			MainImageDto delMain = mainImageService.getFile(no);
//			파일삭제
			SFTPsender sFTPsender = new SFTPsender();
			for (PostImageDto d : del) {
//				File file = new File(d.getFilePath());
				if(sFTPsender.deleteFile(d.getFilename(), 1)) {
					System.out.println("성공");
				}
			}

			sFTPsender.deleteFile(delMain.getFilename(), 0);
			
//			DB삭제
			postImageService.delPost(no);
			mainImageService.delPost(no);
			System.out.println("오케이");
//			태그삭제
			tagService.delPost(no);
//			좋아요 삭제
			boardService.delPost(no);
			System.out.println("마지막");
			return "OK";
		} catch (Exception e) {
			System.out.println("에러");
			return "FALSE";
		}

	}

	@ApiOperation(value = "해당 num 만큼 게시물 추가, 게시물 없으면 End Page 리턴", response = String.class)
	@GetMapping("/paging")
	public ResponseEntity<List<AllMainView>> paging(@RequestParam("location") String location,
			@RequestParam("num") String num) {
		List<LocationInfo> result = new LinkedList<>();
//		한번에 보여줄 posts갯수
		int idx = 6;
//		시작페이지
		int start = Integer.parseInt(num) * idx;
		try {
			List<Board> dto = boardService.getLocationIdxBoard(location, start, idx);
			if(dto.size()==0) {
				return new ResponseEntity("End Page", HttpStatus.FAILED_DEPENDENCY);
			}
			for (Board a : dto) {
				System.out.println(a);
				LocationInfo data = new LocationInfo();
//			data에 Board값 넣기
				data.setBoard(a);
//			메인 이미지 경로 가져와서 넣기
				Optional<MainImage> d = mainImageService.findById(a.getId());
				data.setFilePath(FileMainSrc + d.get().getFilename());
//			tag가져와서 넣기
				data.setTags(tagService.findTagId(a.getId()));
				System.out.println(data.getTags());

				result.add(data);
			}
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("에러");
			return new ResponseEntity(e, HttpStatus.FAILED_DEPENDENCY);

		}
	}
	
	@ApiOperation(value = "해당 지역에 있는 모든 게시물 조회", response = String.class)
	@GetMapping("/allview")
	public ResponseEntity<List<AllMainView>> allview(@RequestParam("location") String location) {
		System.out.println(location);
		try {
//			List<AllMainView> dto = mainImageService.getAllBoard();
			List<LocationInfo> result = new LinkedList<>();
			List<Board> dto = boardService.getLocationBoard(location);
			System.out.println(dto.get(0).toString());
//			List<Map<String, String>> result = new LinkedList<Map<String,String>>();
			System.out.println("Main Image : ");
			for (Board a : dto) {
				LocationInfo data = new LocationInfo();
//				data에 Board값 넣기
				data.setBoard(a);
//				메인 이미지 경로 가져와서 넣기
				Optional<MainImage> d = mainImageService.findById(a.getId());
				data.setFilePath(FileMainSrc + d.get().getFilename());
//				tag가져와서 넣기
				data.setTags(tagService.findTagId(a.getId()));
				System.out.println(data.getTags());

				result.add(data);
			}
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("에러");
			return new ResponseEntity(e, HttpStatus.FAILED_DEPENDENCY);

		}

	}

	@ApiOperation(value = "게시물 id를 통해 게시물 상세보기", response = String.class)
	@GetMapping("/getposts")
	public ResponseEntity<List<AllView>> getpost(@RequestParam("id") String id) {
		long no = Long.parseLong(id);
//		조회수 업로드하기
		
//		내가 좋아요했는지 확인
		try {
			List<AllView> dto = postImageService.findSubImg(no);

			List<Map<String, String>> result = new LinkedList<Map<String, String>>();

			for (AllView a : dto) {
				Map<String, String> m = new HashMap<String, String>();
				System.out.println("아이디 " + a.getId());
				System.out.println("작성자 : " + a.getAuthor());
				System.out.println("파일 : " + a.getFilename());
				m.put("id", Long.toString(a.getId()));
				m.put("author", a.getAuthor());
				m.put("filepath", FileSubSrc + a.getFilename());
				result.add(m);
			}
			AllMainView all = mainImageService.findMainImg(no);
			Map<String, String> m = new HashMap<String, String>();

			m.put("id", Long.toString(all.getId()));
			m.put("author", all.getAuthor());
			m.put("filepath", FileMainSrc + all.getFilename());
			result.add(m);

			return new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e, HttpStatus.FAILED_DEPENDENCY);
		}

	}
	@ApiOperation(value = "게시물 업로드", response = String.class)
	@PostMapping("/requestupload")
	public String write(@RequestParam("main") MultipartFile main, @RequestParam("file") List<MultipartFile> files,
			BoardDto boardDto, @RequestParam("writer") String writer, @RequestParam("location") String location,
			@RequestParam("nation") String nation, @RequestParam("tags") List<String> tags) {
		long id = 0;
		
		
		
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
			String fPath = "/home/mainImg/"+ fname;
			SFTPsender sFTPsender = new SFTPsender();
			File file = new File(fname);
	        file.createNewFile();
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.write(main.getBytes());
	        fos.close();
	        try {
//	        	파일명, 파일
	        	sFTPsender.uploadFile(fname, file,0);
			}catch(Exception e)
			{			
				e.printStackTrace();
			}
	        
	      
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
				String filePath = "/home/subImg/"+ filename;
				SFTPsender sFTPsender = new SFTPsender();

		        File file = new File(filename);
		        file.createNewFile();
		        FileOutputStream fos = new FileOutputStream(file);
		        fos.write(mf.getBytes());
		        fos.close();
				try {
//		        	파일명, 파일
		        	sFTPsender.uploadFile(filename, file,1);
				}catch(Exception e)
				{			
					e.printStackTrace();
				}


				
			
				
		
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
	return"ok";

	}
}
