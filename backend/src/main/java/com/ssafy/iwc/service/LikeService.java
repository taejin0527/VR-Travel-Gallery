package com.ssafy.iwc.service;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.model.MultiId;

@Service
public interface LikeService {
	public int findLike(MultiId multiId);
}
