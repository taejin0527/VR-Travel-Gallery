package com.ssafy.iwc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.iwc.model.Like;
import com.ssafy.iwc.model.MultiId;
import com.ssafy.iwc.repository.LikeRepository;

@Service
public class LikeServiceImpl implements LikeService{

	@Autowired
	LikeRepository likeRepository;
	
	@Override
	public int findLike(MultiId multiId) {
		int likes = likeRepository.findLike(multiId.getUsername(),multiId.getPostsid());
		
		return likes;
	}

}
