package com.programsji.bo;

import java.io.Serializable;

public class Hobby implements Serializable {

	int id;
	String hobbyname;

	public Hobby(int id, String hobbyname) {
		this.id = id;
		this.hobbyname = hobbyname;
	}

	public String getHobbyname() {
		return hobbyname;
	}

	public void setHobbyname(String hobbyname) {
		this.hobbyname = hobbyname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
