package com.ssafy.iwc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.ssafy.iwc.dto.PayInfoDto;
import com.ssafy.iwc.dto.PostImageDto;
import com.ssafy.iwc.dto.TagDto;
import com.ssafy.iwc.model.AllMainView;
import com.ssafy.iwc.model.AllView;
import com.ssafy.iwc.model.Board;
import com.ssafy.iwc.model.LocationInfo;
import com.ssafy.iwc.model.MainImage;
import com.ssafy.iwc.model.MultiId;
import com.ssafy.iwc.model.PayInfo;
import com.ssafy.iwc.service.BoardService;
import com.ssafy.iwc.service.LikeService;
import com.ssafy.iwc.service.MainImageService;
import com.ssafy.iwc.service.PayInfoService;
import com.ssafy.iwc.service.PostImageService;

import com.ssafy.iwc.service.TagService;
import com.ssafy.iwc.service.UserService;
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
	@Autowired
	private LikeService likeService;
	@Autowired
	private PayInfoService payInfoService;
	@Autowired
	private UserService userService;
	
//	????????? ??????
	@Value("${yacht.app.FileMainSrc}")
	private String FileMainSrc;
	@Value("${yacht.app.FileSubSrc}")
	private String FileSubSrc;
	
	
	@ApiOperation(value = "????????? ??????????????? ????????? id?????? get???????????? ??????", response = String.class)
	@GetMapping("/increaseview")
	public void increaseview(@RequestParam("id") String id) {
		long Id = Long.parseLong(id);
		boardService.increaseView(Id);
	}
	
	
	@ApiOperation(value = "location,num??? ??????????????? ???????????? ??????", response = String.class)
	@GetMapping("/eachsearch")
	public ResponseEntity search(@RequestParam("location") String location,
			@RequestParam("num") String num,@RequestParam("searchData")String searchData) {
		List<LocationInfo> result = new LinkedList<>();
		searchData = "%"+searchData+"%";
//		????????? ????????? posts??????
		int idx = 6;
//		???????????????
		int start = Integer.parseInt(num) * idx;
		List<Long> getPostsNum = boardService.getPostsNum(location,searchData,start,idx);
		if(getPostsNum.size()==0) {
			return new ResponseEntity("End Page",  HttpStatus.OK);
		}
		for(long sidx : getPostsNum) {
			LocationInfo data = new LocationInfo();
//			data??? Board??? ??????
			
			data.setBoard(boardService.getPost(sidx));
//			?????? ????????? ?????? ???????????? ??????
			Optional<MainImage> d = mainImageService.findById(sidx);
			data.setFilePath(FileMainSrc + d.get().getFilename());
//			tag???????????? ??????
			data.setTags(tagService.findTagId(sidx));
			System.out.println(data.getTags());

			result.add(data);
		}
		
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "?????????????????? num??? ??????????????? ???????????? ??????", response = String.class)
	@GetMapping("/allsearch")
	public ResponseEntity allsearch(@RequestParam("num") String num,@RequestParam("searchData")String searchData) {
		List<LocationInfo> result = new LinkedList<>();
		searchData = "%"+searchData+"%";
//		????????? ????????? posts??????
		int idx = 6;
//		???????????????
		int start = Integer.parseInt(num) * idx;
		List<Long> getPostsNum = boardService.getAllPostsNum(searchData,start,idx);
		if(getPostsNum.size()==0) {
			return new ResponseEntity("End Page",  HttpStatus.OK);
		}
		for(long sidx : getPostsNum) {
			LocationInfo data = new LocationInfo();
//			data??? Board??? ??????
			data.setBoard(boardService.getPost(sidx));
//			?????? ????????? ?????? ???????????? ??????
			Optional<MainImage> d = mainImageService.findById(sidx);
			data.setFilePath(FileMainSrc + d.get().getFilename());
//			tag???????????? ??????
			data.setTags(tagService.findTagId(sidx));
			System.out.println(data.getTags());

			result.add(data);
		}
		
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "????????? id??? ????????? ???????????? ?????? ????????? ??????", response = String.class)
	@DeleteMapping("/delpost")
	public String delpost(@RequestParam("id") String id) {
		Long no = Long.parseLong(id);
		try {
//			????????? ?????? ????????????
			List<PostImageDto> del = postImageService.getFile(no);
			MainImageDto delMain = mainImageService.getFile(no);
//			????????????
			SFTPsender sFTPsender = new SFTPsender();
			for (PostImageDto d : del) {
//				File file = new File(d.getFilePath());
				if(sFTPsender.deleteFile(d.getFilename(), 1)) {
					System.out.println("??????");
				}
			}

			sFTPsender.deleteFile(delMain.getFilename(), 0);
			
//			DB ????????? ??????
			postImageService.delPost(no);
			mainImageService.delPost(no);
			System.out.println("?????????");
//			????????????
			tagService.delPost(no);
//			????????? ??????
			likeService.deleteAll(no);
//			????????? ??????
			boardService.delPost(no);
			System.out.println("?????????");
			return "OK";
		} catch (Exception e) {
			System.out.println("??????");
			return "FALSE";
		}

	}
	@ApiOperation(value = "??????s num ?????? ????????? ??????, ????????? ????????? End Page ??????", response = String.class)
	@GetMapping("/paging")
	public ResponseEntity<List<AllMainView>> paging(@RequestParam("location") String location,
			@RequestParam("num") String num) {
		List<LocationInfo> result = new LinkedList<>();
//		????????? ????????? posts??????
		int idx = 6;
//		???????????????
		int start = Integer.parseInt(num) * idx;
		try {
			List<Board> dto = boardService.getLocationIdxBoard(location, start, idx);
			if(dto.size()==0) {
				return new ResponseEntity("End Page",  HttpStatus.OK);
			}
			for (Board a : dto) {
				System.out.println(a);
				LocationInfo data = new LocationInfo();
//			data??? Board??? ??????
				data.setBoard(a);
//			?????? ????????? ?????? ???????????? ??????
				Optional<MainImage> d = mainImageService.findById(a.getId());
				data.setFilePath(FileMainSrc + d.get().getFilename());
//			tag???????????? ??????
				data.setTags(tagService.findTagId(a.getId()));
				System.out.println(data.getTags());

				result.add(data);
			}
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("??????");
			return new ResponseEntity(e, HttpStatus.FAILED_DEPENDENCY);

		}
	}
	@ApiOperation(value = "username??? ?????? ??????????????? ?????? ????????????", response = String.class)
	@GetMapping("/postgetusername")
	public ResponseEntity postgetusername(@RequestParam("username")String username,@RequestParam("num") String num) {
		List<LocationInfo> result = new LinkedList<>();
		int idx = 6;
		int start = Integer.parseInt(num) * idx;
		try {
			List<Board> board = boardService.getUsernameBoard(username,start,idx);
			if(board.size()==0) {
				return new ResponseEntity("End Page",  HttpStatus.OK);
			}
			for (Board a : board) {
				
				LocationInfo data = new LocationInfo();
//			data??? Board??? ??????
				data.setBoard(a);
//			?????? ????????? ?????? ???????????? ??????
				Optional<MainImage> d = mainImageService.findById(a.getId());
				
				data.setFilePath(FileMainSrc + d.get().getFilename());
//			tag???????????? ??????
				data.setTags(tagService.findTagId(a.getId()));
				System.out.println(data.getTags());

				result.add(data);
			}
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("??????");
			return new ResponseEntity(e, HttpStatus.FAILED_DEPENDENCY);

		}
		
		
		
		
	}
	@ApiOperation(value="username??? ????????? ????????? ?????? ?????? ????????? ????????? ??????")
	@GetMapping("/mypay")
	public ResponseEntity mypay(@RequestParam("username")String username,@RequestParam("num") String num) {
		List<LocationInfo> result = new LinkedList<>();
		int idx = 6;
		int start = Integer.parseInt(num) * idx;
		try {
			List<Board> board = boardService.getPayBoard(username,start,idx);
			if(board.size()==0) {
				return new ResponseEntity("End Page",  HttpStatus.OK);
			}
			for (Board a : board) {
				
				LocationInfo data = new LocationInfo();
//			data??? Board??? ??????
				data.setBoard(a);
//			?????? ????????? ?????? ???????????? ??????
				Optional<MainImage> d = mainImageService.findById(a.getId());
				
				data.setFilePath(FileMainSrc + d.get().getFilename());
//			tag???????????? ??????
				data.setTags(tagService.findTagId(a.getId()));
				

				result.add(data);
			}
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("??????");
			return new ResponseEntity(e, HttpStatus.FAILED_DEPENDENCY);

		}
	}
	@ApiOperation(value = "?????? ????????? ?????? ?????? ????????? ??????", response = String.class)
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
//				data??? Board??? ??????
				data.setBoard(a);
//				?????? ????????? ?????? ???????????? ??????
				Optional<MainImage> d = mainImageService.findById(a.getId());
				data.setFilePath(FileMainSrc + d.get().getFilename());
//				tag???????????? ??????
				data.setTags(tagService.findTagId(a.getId()));
				System.out.println(data.getTags());

				result.add(data);
			}
			return new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("??????");
			return new ResponseEntity(e, HttpStatus.FAILED_DEPENDENCY);

		}

	}
	
	@ApiOperation(value = "postsid, username, ????????????(true or false)??? ????????? true or false??????", response = String.class)
	@GetMapping("/fixlike")
	public ResponseEntity fixlike(@RequestParam("id") String id, @RequestParam("username") String username, @RequestParam("curr") String curr) {
		String result;
		if(curr.equals("true")) { //????????? ???????????? -> ??????
			try {
//				????????? ????????????
				MultiId multiId = new MultiId();
				multiId.setPostsid(Long.parseLong(id));
				multiId.setUsername(username);
				int flag = likeService.findLike(multiId);
				if(flag==0) {
					result= "????????? ???????????????.";
				}else {
					likeService.deleteLike(id,username);
					result= "false";
				}
				
				
				
				
			}catch(Exception e) {
				result= "??????";
			}
		}else { //????????? ????????? -> ??????
			try {
//				????????? ????????????
				MultiId multiId = new MultiId();
				multiId.setPostsid(Long.parseLong(id));
				multiId.setUsername(username);
				int flag = likeService.findLike(multiId);
				if(flag==0) {
					likeService.addLike(id,username);
					result = "true";
				}else {
					result= "????????? ???????????????.";
				}
				
				
				
			}catch(Exception e) {
				result= "??????";
			}
		}
		
		
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "????????? id??? ?????? ????????? ????????????,username?????? ????????? ??????", response = String.class)
	@GetMapping("/getposts")
	public ResponseEntity<List<AllView>> getpost(@RequestParam("id") String id,@RequestParam("username") String username) {
		long no = Long.parseLong(id);
//		????????? ???????????????
		

		try {
//			?????? ?????????????????? ??????
			MultiId mid = new MultiId();
			mid.setPostsid(no);
			mid.setUsername(username);
			int like = likeService.findLike(mid);
			System.out.println(like);
			
			List<AllView> dto = postImageService.findSubImg(no);

//			List<Map<String, String>> result = new LinkedList<Map<String, String>>();
			LocationInfo locationinfo = new LocationInfo();
			List<String> subImg = new LinkedList<String>();
			for (AllView a : dto) {
//			sub????????? ?????? ??????
				subImg.add(FileSubSrc+a.getFilename());
			}
			locationinfo.setSubPath(subImg);
//			??????????????? ????????????
			Optional<MainImage> d = mainImageService.findById(no);
			locationinfo.setFilePath(FileMainSrc + d.get().getFilename());
//			????????? ????????????
			if(like==0) {
				locationinfo.setLike("false");
				
			}
			else {
				locationinfo.setLike("true");
				
			}
//			??????????????? ??????
			locationinfo.setBoard(boardService.findById(no));
//			???????????? ??????
			locationinfo.setTags(tagService.findTagId(no));
			

			return new ResponseEntity(locationinfo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e, HttpStatus.FAILED_DEPENDENCY);
		}

	}
	
	@ApiOperation(value = "????????? ???????????? ?????? true or false", response = String.class)
	@GetMapping("/payrequest")
	public String payrequest(@RequestParam("userid")String userid,@RequestParam("username")String username, @RequestParam("id") String id) {
		String result= "";
		long userId = Long.parseLong(userid);
		boolean answer = userService.findUser(userId,username);
		System.out.println(answer);
//		?????????????????? ??????
		if(answer) {
			long no = Long.parseLong(id);
			int check = payInfoService.getPayRequest(username,no);
			if(check==0) {
				result="false";
			}else {
				result = "true";
			}
		}else {
			result="????????? ???????????????.";
		}
		
		
		
		return result;
	}
	@ApiOperation(value = "??????????????? ????????????????????? ????????????", response = String.class)
	@GetMapping("/paypost")
	public ResponseEntity paypost(@RequestParam("userid")String userid,@RequestParam("username")String username, @RequestParam("id") String id) {
		long no = Long.parseLong(id);
		String result= "";
		long userId = Long.parseLong(userid);
		boolean answer = userService.findUser(userId,username);
		if(answer) {
			try {
				PayInfoDto payInfoDto = new PayInfoDto();
				payInfoDto.setCost(30);
				payInfoDto.setUsername(username);
				payInfoDto.setPostid(no);
				boolean flag = payInfoService.saveInfo(payInfoDto);
				if(flag) {
					result="?????? ??????";
					return new ResponseEntity(result, HttpStatus.OK);
				}else {
					result = "?????? ??????";
					return new ResponseEntity(result, HttpStatus.FAILED_DEPENDENCY);
				}
				
			}catch(Exception e) {
				
				result = "?????? ??????";
				return new ResponseEntity(result, HttpStatus.FAILED_DEPENDENCY);
			}
		}else {
			result="????????? ???????????????.";
			return new ResponseEntity(result, HttpStatus.FAILED_DEPENDENCY);
		}
		
		
	}
	
	
	@ApiOperation(value = "????????? ?????????", response = String.class)
	@PostMapping("/requestupload")
	public String write(@RequestParam("main") MultipartFile main, @RequestParam("file") List<MultipartFile> files,
			BoardDto boardDto, @RequestParam("writer") String writer, @RequestParam("location") String location,
			@RequestParam("nation") String nation, @RequestParam("tags") List<String> tags,@RequestParam("premium")String premium) {
		long id = 0;
		
//		???????????? ?????? ?????? ?????????
		
//		System.out.println(addTime);
		try {
			//????????? ??????
			boardDto.setAuthor(writer);
			boardDto.setLocation(location);
			boardDto.setNation(nation);
			if (premium.equals("true")) {
				boardDto.setPremium(true);
			} else {
				boardDto.setPremium(false);
			}
			id = boardService.savePost(boardDto);
			PayInfoDto payInfoDto = new PayInfoDto();
			payInfoDto.setCost(0);
			payInfoDto.setUsername(writer);
			payInfoDto.setPostid(id);
			payInfoService.createInfo(payInfoDto);
			//?????? ????????? ??????
			String origname = main.getOriginalFilename();
			String expend="";
			for(int i=0;i<origname.length();i++) {
				if(origname.charAt(i)=='.') {
					expend = origname.substring(i,origname.length());
					break;
				}
			}
			Date time = new Date();
			String addTime = time.toString();
			String fname = new MD5Generator(origname+addTime).toString()+expend;
			String fPath = "/home/mainImg/"+ fname;
			SFTPsender sFTPsender = new SFTPsender();
			File file = new File(fname);
	        file.createNewFile();
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.write(main.getBytes());
	        fos.close();
	        try {
//	        	?????????, ??????
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
		System.out.println("???????????????");
		System.out.println(files.size());
		for(MultipartFile mf : files) {
			try {
				

				String origFilename = mf.getOriginalFilename();
				System.out.println(origFilename);
				String expend="";
				for(int i=0;i<origFilename.length();i++) {
					if(origFilename.charAt(i)=='.') {
						expend = origFilename.substring(i,origFilename.length());
						break;
					}
				}
				Date time = new Date();
				String addTime = time.toString();
//				????????? ???????????? MD5Generator?????? ????????????
				String filename = new MD5Generator(origFilename+addTime).toString()+expend;
				String filePath = "/home/subImg/"+ filename;
				SFTPsender sFTPsender = new SFTPsender();

		        File file = new File(filename);
		        file.createNewFile();
		        FileOutputStream fos = new FileOutputStream(file);
		        fos.write(mf.getBytes());
		        fos.close();
				try {
//		        	?????????, ??????
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
