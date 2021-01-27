package com.ssafy.iwc.dto;

import java.time.LocalDateTime;

import com.ssafy.iwc.model.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
	private long id;
	private String author;
	
	private long good;
	private long views;
	private String location;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public Board toEntity() {
		Board build = Board.builder()
				.id(id)
				.author(author)
				.good(good)
				.views(views)
				.createdDate(createdDate)
				.location(location)
				.build();
		return build;
	}
	@Builder
	public BoardDto(long id, String author, long good, long views, String location, LocalDateTime createdDate,
			LocalDateTime modifiedDate) {
		
		this.id = id;
		this.author = author;
		this.good = good;
		this.views = views;
		this.location = location;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	

	
	

	
}
