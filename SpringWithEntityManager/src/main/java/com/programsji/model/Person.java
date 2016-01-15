package com.programsji.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "person")
public class Person implements Serializable {
	private static final long serialVersionUID = 7906638692609143903L;
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;
	
	@Column(name = "fname")
	String fname;
	@Column(name = "lname")
	String lname;
	@Column(name = "age")
	int age;
	@Column(name = "salary")
	float salary;
	@Column(name = "dob")
	Date dob;

	public Person() {

	}

	public Person(int id, String fname, String lname, int age, float salary,
			Date dob) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.salary = salary;
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

}