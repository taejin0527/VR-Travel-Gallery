package com.ssafy.iwc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(	name = "subimg"
)
@Getter
@Setter
//@NoArgsConstructor, @AllArgsConstructor, @RequiredArgsConstructor - 클래스의 생성자를 만들어줌
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {
//	@Id -> DB의 Pk의 값을 지정

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long no;
	
	
//	@Column(nullable = false)
	private long id;
	
	
//	@Column -> DB테이블 내의 컬럼을 결정, name속성 : 컬럼명을 지정, length속성 : 데이타가 가지는 길이(기본 255 - 문자)
//	숫자는 precision과 scale로 조정, nullable속성 : null값이 가능한지 판단
 	@Column(nullable = false)
	private String origFilename;
	
	@Column(nullable = false)
	private String filename;
	
	@Column(nullable = false)
	private String filePath;



//	빌더 패턴을 이용한 객체 생성 메소드/클래스를 만들어줌
	@Builder
	public PostImage(long no, long id, String origFilename, String filename, String filePath) {
	
		this.no = no;
		this.id = id;
		this.origFilename = origFilename;
		this.filename = filename;
		this.filePath = filePath;
	}
	
	
	
	
	
}
