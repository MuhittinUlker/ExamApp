package com.muhittinu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilities {

	static Scanner scanner = new Scanner(System.in);
	static int intInput;
	static String stringInput;
	
	public int intFromUser(String message) {
		System.out.print("\u001B[36m"+message+"\u001B[0m");
		try {
			intInput = scanner.nextInt();scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("\u001B[33mLütfen doğru formatta giriş yapın. "+e.getMessage()+"\u001B[0m");
		}
		return intInput;
	}
	
	public String stringFromUser(String message) {
		System.out.print("\u001B[36m"+message+"\u001B[0m");
		try {
			stringInput = scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("\u001B[33mLütfen doğru formatta giriş yapın. "+e.getMessage()+"\u001B[0m");
		}
		return stringInput;
	}
}
