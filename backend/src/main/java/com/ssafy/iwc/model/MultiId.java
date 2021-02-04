package com.ssafy.iwc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class MultiId implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String userid;

	long postsid;

}
