package com.ssafy.iwc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) //JPA에게 해당 Entity는 Auditing기능을 사용함을 알림
public class Board {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(length=10,nullable =false)
	private String author;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	@Column
	private int postImageId;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;

	@Builder
	public Board(int id, String author, String content, int postImageId, LocalDateTime createdDate,
			LocalDateTime modifiedDate) {
	
		this.id = id;
		this.author = author;
		this.content = content;
		this.postImageId = postImageId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	
	
	
	
}
