package com.ssafy.iwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.iwc.model.PostImage;
@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Integer>{

}
