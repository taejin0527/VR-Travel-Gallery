package com.ssafy.iwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.iwc.model.MainImage;

@Repository
public interface MainImageRepository extends JpaRepository<MainImage, Long>{

}
