package com.programsji.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

//@Component
@XmlRootElement(name = "person")
// @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Person {

	String name;

	int age;
	float salary;
	Date dob;
	List<Hobby> hobbies = new ArrayList<Hobby>();

	public Person() {

	}

	public Person(String name, int age, float salary, Date dob) {
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.dob = dob;
	}

	public Person(String name, int age, float salary, Date dob,
			List<Hobby> hobbies) {
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.dob = dob;
		this.hobbies = hobbies;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@XmlElement
	public int getAge() {
		return age;
	}

	@JsonProperty(value = "age")
	public void setAge(int age) {
		this.age = age;
	}

	@XmlElement
	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@JsonIgnore
	@XmlElement
	public Date getDob() {
		return dob;
	}

	@JsonProperty(value = "dob")
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

}
