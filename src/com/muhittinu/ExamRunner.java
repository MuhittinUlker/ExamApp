package com.muhittinu;

public class ExamRunner {

	static Database db = new Database();

	public static void main(String[] args) {

		db.generateQuestions();
		db.studentCreator();
		db.questionCreator();

		ExamManager examManager = new ExamManager();
		examManager.runnerMenu();

	}

}
