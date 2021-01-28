
package com.ssafy.iwc.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
	public long saveFile(PostImageDto postImageDto) {
		return postImageRepository.save(postImageDto.toEntity()).getId();
	}
	
	@Transactional
	public List<PostImageDto> getFile(long id) {
		System.out.println("�������");
		List<PostImage> postImage = postImageRepository.findPostImageById(id);
		List<PostImageDto> postImageDto = new LinkedList<PostImageDto>();
		for(PostImage p : postImage) {
			
			PostImageDto dto = PostImageDto.builder()
					.id(id)
					.origFilename(p.getOrigFilename())
					.filename(p.getFilename())
					.filePath(p.getFilePath())
					.build();
			postImageDto.add(dto);
		};

		
		return postImageDto;
				
	}
	
}
