package com.ssafy.iwc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.dto.MainImageDto;
import com.ssafy.iwc.model.AllMainView;

@Service
public interface MainImageService {
	public void saveFile(MainImageDto mainImageDto);
	public List<AllMainView> getAllBoard();
	public MainImageDto getFile(long id);
	public AllMainView findMainImg(long no);
	public void delPost(Long no);
}
