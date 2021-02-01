package com.ssafy.iwc.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Getter
public class AllView {
	@Id
	private long id;
	private String author;
	
	
	private String filename;
	

	public AllView() {

	}


	public AllView(long id, String author, String filename) {
	
		this.id = id;
		this.author = author;
		this.filename = filename;
	}
	
	
}
