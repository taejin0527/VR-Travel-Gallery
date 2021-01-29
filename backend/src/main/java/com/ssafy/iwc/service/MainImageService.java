package com.ssafy.iwc.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.dto.MainImageDto;
import com.ssafy.iwc.model.MainImage;
import com.ssafy.iwc.repository.MainImageRepository;

@Service
public class MainImageService {
	private MainImageRepository mainImageRepository;
	
	
	public MainImageService(MainImageRepository mainImageRepository) {
		this.mainImageRepository = mainImageRepository;
	}
	
	@Transactional
	public void saveFile(MainImageDto mainImageDto) {
		mainImageRepository.save(mainImageDto.toEntity());
	}
	
	@Transactional
	public MainImageDto getFile(long id) {
		MainImage mainImage = mainImageRepository.findById(id).get();
		
		MainImageDto mainImageDto = MainImageDto.builder()
				.id(id)
				.origFilename(mainImage.getOrigFilename())
				.filename(mainImage.getFilename())
				.filePath(mainImage.getFilePath())
				.build();
		return mainImageDto;
	}
}
