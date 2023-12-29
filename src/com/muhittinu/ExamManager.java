package com.muhittinu;

import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

public class ExamManager {

	static Menu menu = new Menu();
	static FileOperator fo = new FileOperator();
	Utilities util = new Utilities();

	private final String NAME_SURNAME_REGEX = "^[a-zA-ZçÇğĞıİöÖşŞüÜ]+$";
	private final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

	private LocalTime examStartTime = null;
	private LocalTime examEndTime = null;

	public LocalTime getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(LocalTime examStartTime) {
		this.examStartTime = examStartTime;
	}

	public LocalTime getExamEndTime() {
		return examEndTime;
	}

	public void setExamEndTime(LocalTime examEndTime) {
		this.examEndTime = examEndTime;
	}

	public void runnerMenu() {
		do {
			menu.mainMenu();
			int selection = util.intFromUser("Seçiminiz: ");
			switch (selection) {
			case 1:
				menu.registerMenu();
				register();
				break;
			case 2:
				Student loggedStudent = login();
				if (loggedStudent != null) {
					loggedMenu(loggedStudent);
				} else {
					System.out.println("Giriş bilgileriniz hatalı..");
				}
				break;
			case 0:
				System.out.println("Çıkış yapıldı.");
				System.exit(0);
				break;
			default:
				System.out.println("0-2 arası giriş yapın.");
				break;
			}
		} while (true);
	}

	public void loggedMenu(Student student) {
		int selection;
		menu.loginMenu(student);
		selection = util.intFromUser("Seçiminiz: ");
		switch (selection) {
		case 1:
			startExam(student);
			break;
		case 0:
			runnerMenu();
			break;
		default:
			System.out.println("Lütfen 0-2 arası giriş yapın.");
			break;
		}
	}

	private void startExam(Student student) {
		if (student.getStatus().equals(EStatus.ENTERED)) {
			System.out.println("Öğrenci sınava daha önce girmiş..");
			return;
		}
		setExamStartTime(LocalTime.now());
		for (Question question : ExamRunner.db.questions) {
			System.out.println(question.getNo() + " " + question.getPuan() + "\n" + question.getContent());
			for (Map.Entry<String, String> entry : question.getOptions().entrySet()) {
				System.out.print(entry + "\t");
			}
			String answer = util.stringFromUser("Cevabınız: ");
			student.getAnswers().add(answer);
			if (question.getCorrectOption().equals(answer)) {
				student.setScore(student.getScore() + Integer.parseInt(question.getPuan().substring(0, 2)));
			}
		}
		student.setStatus(EStatus.ENTERED);
		setExamEndTime(LocalTime.now());
	}

	public Student login() {
		String number = util.stringFromUser("Okul Numaranızı Girin: ");
		String password = util.stringFromUser("Şifrenizi Girin: ");
		Optional<Student> optStudent = findStudentByNumberAndPassword(number, password);
		if (optStudent.isPresent()) {
			return (optStudent.get());
		}
		return null;
	}

	private Optional<Student> findStudentByNumberAndPassword(String number, String password) {
		for (Student student : ExamRunner.db.students) {
			if (student.getNumber().equals(number) && student.getPassword().equals(password)) {
				return Optional.of(student);
			}
		}
		return Optional.empty();
	}

	public void register() {
		do {
			try {
				while (true) {
					String studentName = util.stringFromUser("Adınızı girin: ");
					if (!isNameValid(studentName)) {
						System.out.println("\u001B[33mİsim formata uygun değil..\u001B[0m");
						continue;
					}
					while (true) {
						String studentSurname = util.stringFromUser("Soyadınızı girin: ");
						if (!isNameValid(studentSurname)) {
							System.out.println("\u001B[33mSoyisim formata uygun değil..\u001B[0m");
							continue;
						}
						while (true) {
							String password = util.stringFromUser("Şifrenizi girin: ");
							if (!isPasswordValid(password)) {
								System.out.println("\u001B[33mŞifreniz formata uygun değil..\u001B[0m");
								continue;
							}
							while (true) {
								String rePassword = util.stringFromUser("Şifrenizi tekrar girin: ");
								if (!rePassword.equals(password)) {
									System.out.println("\u001B[33mŞifreleriniz eşleşmiyor..\u001B[0m");
									continue;
								}
								String number = util.stringFromUser("Okul numaranızı girin: ");

								Student student = new Student(studentName, studentSurname, password, number);
								for (Student studentInList : ExamRunner.db.students) {
									if (student.getNumber().equals(studentInList.getNumber())) {
										System.out.println("\u001B[33mÖğrenci zaten sisteme kayıtlı..\u001B[0m");
										return;
									}
								}
								ExamRunner.db.students.add(student);
								fo.writeStudents(ExamRunner.db.students);
								System.out.println("Kayıt başarılı.Giriş yapabilirsiniz " + studentName);
								return;
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println("\u001B[33mBir hata oluştu.. " + e.getMessage() + "\u001B[0m");
			}
		} while (true);
	}

	private boolean isPasswordValid(String password) {
		if (password.matches(PASSWORD_REGEX)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNameValid(String studentName) {
		if (studentName.matches(NAME_SURNAME_REGEX)) {
			return true;
		} else {
			return false;
		}
	}

}
