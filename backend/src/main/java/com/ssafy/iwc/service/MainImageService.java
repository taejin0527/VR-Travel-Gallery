package com.ssafy.iwc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.dto.MainImageDto;
import com.ssafy.iwc.model.AllView;

@Service
public interface MainImageService {
	public void saveFile(MainImageDto mainImageDto);
	public List<AllView> getAllBoard();
	public MainImageDto getFile(long id);
}
