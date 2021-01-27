package com.ssafy.iwc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@EntityListeners(AuditingEntityListener.class) //JPA에게 해당 Entity는 Auditing기능을 사용함을 알림
public class Board {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=10,nullable =false)
	private String author;
	
	@Column
	@ColumnDefault("0")
	private long good;
	
	@Column
	@ColumnDefault("0")
	private long views;
	
	@Column(nullable = false, length=100)
	private String location;
	
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdDate;
	
	
	@UpdateTimestamp
	private LocalDateTime modifiedDate;

	


	@Builder
	public Board(long id, String author, long good, long views, String location, 
			LocalDateTime createdDate, LocalDateTime modifiedDate) {
		
		this.id = id;
		this.author = author;
		this.good = good;
		this.views = views;
		this.location = location;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	
	
	
	
	
	
}
