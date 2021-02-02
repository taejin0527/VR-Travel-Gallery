package com.ssafy.iwc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.iwc.model.Tag;
@Service
public interface TagService {
	public List<Tag> findTagId(Long no);
}
