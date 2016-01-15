package com.programsji.bo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	int id;
	int rollno;
	String fname;
	String lname;
	int age;
	List<String> hobbies;

	public Person() {

	}

	public Person(int id, int rollno, String fname, String lname, int age,
			List<String> hobbies) {
		this.id = id;
		this.rollno = rollno;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.hobbies = hobbies;

	}

	@XmlElement(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "rollno")
	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	@XmlElement(name = "fname")
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@XmlElement(name = "lname")
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@XmlElement(name = "age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@XmlElement(name = "hobbies")
	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

}
