package com.ssafy.iwc.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "like")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

	@EmbeddedId
	private String userid;

	private long postsid;
	
	
	
}
