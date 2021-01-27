
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
		List<PostImage> postImage = (List<PostImage>) postImageRepository.findById(id).get();
		List<PostImageDto> postImageDto = new LinkedList<PostImageDto>();
		for(int i=0;i<postImage.size();i++) {
			PostImageDto dto = PostImageDto.builder()
					
					.id(id)
					.origFilename(postImage.get(i).getOrigFilename())
					.filename(postImage.get(i).getFilename())
					.filePath(postImage.get(i).getFilePath())
					.build();
			postImageDto.add(dto);
			
		}
		
		return postImageDto;
				
	}
	
}
