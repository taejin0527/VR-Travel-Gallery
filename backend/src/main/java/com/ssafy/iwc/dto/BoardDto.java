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
	private int id;
	private String author;
	private String content;
	private int postImageId;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public Board toEntity() {
		Board build = Board.builder()
				.id(id)
				.author(author)
				.content(content)
				.postImageId(postImageId)
				.build();
		return build;
	}
	
	@Builder
	public BoardDto(int id, String author, String content, int postImageId, LocalDateTime createdDate,
			LocalDateTime modifiedDate) {
		this.id = id;
		this.author = author;
		this.content = content;
		this.postImageId = postImageId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	

	
}
