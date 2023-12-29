package com.muhittinu;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Student implements Serializable{

	private String name;
	private String surname;
	private String password;
	private String number;
	private int score;
	private List<String> answers;
	private EStatus status;
	
	public Student(String name, String surname, String password, String okulNo) {
		super();
		this.surname = surname;
		this.name = name;
		this.password = password;
		this.number = okulNo;
		this.answers = new LinkedList<>();
		this.status = EStatus.NOT_ENTERED;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String okulNo) {
		this.number = okulNo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int not) {
		this.score = not;
	}

	@Override
	public String toString() {
		return "No=" + number +" İsim=" + name +" Soyisim="+ surname +" Not=" + score;
	}
	
	
	
}
