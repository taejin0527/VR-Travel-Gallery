package com.ssafy.iwc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mainimg")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainImage {

	@Id
	private long id;
	
	@Column(nullable = false)
	private String origFilename;
	
	@Column(nullable = false)
	private String filename;
	
	@Column(nullable = false)
	private String filePath;

	@Builder
	public MainImage(long id, String origFilename, String filename, String filePath) {
		
		this.id = id;
		this.origFilename = origFilename;
		this.filename = filename;
		this.filePath = filePath;
	}
	
	
	
	
}
