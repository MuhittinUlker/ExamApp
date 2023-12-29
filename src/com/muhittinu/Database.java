package com.muhittinu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Database implements Serializable {

	FileOperator fo = new FileOperator();

	public List<Student> students = new LinkedList<>();
	public List<Question> questions = new LinkedList<>();

	public Database() {

	}

	public void studentCreator() {
		Optional<List<Student>> studentList = fo.readStudents("Students.txt");
		if (studentList.isEmpty()) {
			System.out.println("Sistemde kayıtlı öğrenci yok.");
			this.students = new ArrayList<>();
		} else {
			this.students = studentList.get();
			System.out.println("Sistemde kayıtlı öğrenciler var.");
		}
	}

	public void questionCreator() {
		Optional<List<Question>> questionList = fo.readQuestions("Questions.txt");
		if (!(this.questions.isEmpty())) {
			this.questions = questionList.get();
			System.out.println("Sistemde kayıtlı sorular var.");
		} else {
			System.out.println("Sistemde kayıtlı soru yok.");
		}
	}

	public void generateQuestions() {
		fo.readQuestionFile("Sorular.txt");
	}
}
