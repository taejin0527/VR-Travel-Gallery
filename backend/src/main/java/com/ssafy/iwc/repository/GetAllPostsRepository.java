package com.ssafy.iwc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.iwc.model.AllView;

@Repository
public interface GetAllPostsRepository extends JpaRepository<AllView, Long>{
	@Query(value = "select p.id, p.author,  m.filename  from mainimg m,posts p where m.id = p.id  ", nativeQuery = true)
	List<AllView> findAllBoard();
}

