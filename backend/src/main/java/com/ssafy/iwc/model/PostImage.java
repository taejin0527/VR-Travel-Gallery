package com.ssafy.iwc.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor, @AllArgsConstructor, @RequiredArgsConstructor - Ŭ������ �����ڸ� �������
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {
//	@Id -> DB�� Pk�� ���� ����
	@Id
	@GeneratedValue
	private int id;
	
	
//	@Column -> DB���̺� ���� �÷��� ����, name�Ӽ� : �÷����� ����, length�Ӽ� : ����Ÿ�� ������ ����(�⺻ 255 - ����)
//	���ڴ� precision�� scale�� ����, nullable�Ӽ� : null���� �������� �Ǵ�
 	@Column(nullable = false)
	private String origFilename;
	
	@Column(nullable = false)
	private String filename;
	
	@Column(nullable = false)
	private String filePath;

//	���� ������ �̿��� ��ü ���� �޼ҵ�/Ŭ������ �������
	@Builder
	public PostImage(int id, String origFilename, String filename, String filePath) {
		
		this.id = id;
		this.origFilename = origFilename;
		this.filename = filename;
		this.filePath = filePath;
	}
	
	
	
	
}
