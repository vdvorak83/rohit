package com.programsji.bo;

import java.util.List;

public class Person {
	int id;
	String fname;
	String lname;
	List<String> hobbies;

	public Person() {

	}

	public Person(int id, String fname, String lname, List<String> hobbies) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.hobbies = hobbies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", fname=" + fname + ", lname=" + lname
				+ ", hobbies=" + hobbies + "]";
	}

}
