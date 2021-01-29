
package com.ssafy.iwc.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.dto.PostImageDto;
import com.ssafy.iwc.model.PostImage;
import com.ssafy.iwc.repository.PostImageRepository;

@Service
public class PostImageService {
	private PostImageRepository postImageRepository;
	
	public PostImageService(PostImageRepository postImageRepository) {
		this.postImageRepository = postImageRepository;
	}
	
	@Transactional
	public int saveFile(PostImageDto postImageDto) {
		return postImageRepository.save(postImageDto.toEntity()).getId();
	}
	
	@Transactional
	public PostImageDto getFile(int id) {
		PostImage postImage = postImageRepository.findById(id).get();
		
		PostImageDto postImageDto = PostImageDto.builder()
				.id(id)
				.origFilename(postImage.getOrigFilename())
				.filename(postImage.getFilename())
				.filePath(postImage.getFilePath())
				.build();
		return postImageDto;
				
	}
	
}
