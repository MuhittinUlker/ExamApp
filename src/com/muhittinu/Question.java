package com.muhittinu;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Question implements Serializable{

	private String no;
	private String puan;
	private String content;
	private Map<String, String> options = new LinkedHashMap<>();
	private String correctOption;
	
	
	public Question() {
	}

	public Question(String no, String puan, String content, String correctOption) {
		this.no = no;
		this.puan = puan;
		this.content = content;
		this.correctOption = correctOption;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getPuan() {
		return puan;
	}

	public void setPuan(String puan) {
		this.puan = puan;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}

	@Override
	public String toString() {
		return "Question" + no + " (" + puan + ") - " + content+"\n"+options;
	}
	
	
	
}
