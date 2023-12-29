package com.muhittinu;

public class Menu {

	public void mainMenu() {
		System.out.println("\u001B[32m-_-_-_ ExamApp _-_-_-");
		System.out.println(" ___________________ ");
		System.out.println("|                   |");
		System.out.println("| 1- Register       |");
		System.out.println("| 2- Login          |");
		System.out.println("| 0- Exit           |");
		System.out.println("|___________________|\u001B[0m");
	}
	
	public void loginMenu(Student student) {
		System.out.println("\u001B[32m-_-_-_ Hoşgeldiniz "+ student.getName()+" _-_-_-");
		System.out.println(" ___________________ ");
		System.out.println("|                   |");
		System.out.println("| 1- Start Exam     |");
		System.out.println("| 0- Logout         |");
		System.out.println("|___________________|\u001B[0m");
	}
	
	public void registerMenu() {
		System.out.println("\u001B[32m-_-_-_ Register _-_-_-\u001B[0m");
	}
	
	
}
