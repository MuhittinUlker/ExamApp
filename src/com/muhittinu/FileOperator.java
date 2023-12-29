package com.muhittinu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FileOperator {

	static Utilities util = new Utilities();
	
	public void writeStudents(List<Student> studentList) {
		
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Students.txt"))){
			
			out.writeObject(studentList);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeQuestions(List<Question> questionList) {
		
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Questions.txt"))){
			
			out.writeObject(questionList);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Optional<List<Student>> readStudents(String fileName) {
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName))){
        	List<Student> studentList = (List<Student>)reader.readObject();
        	
        	return Optional.of(studentList);
        	
        } catch (IOException e) {
        	return Optional.empty();
        } catch (ClassNotFoundException e) {
			System.out.println("\u001B[33mError: ClassNotFound"+ e.getMessage()+"\u001B[0m");
		}
        return Optional.of(Collections.emptyList());
	}
	
	public Optional<List<Question>> readQuestions(String fileName) {
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName))){
        	List<Question> questionList = (List<Question>)reader.readObject();
        	
        	return Optional.of(questionList);
        	
        } catch (IOException e) {
        	return Optional.empty();
        } catch (ClassNotFoundException e) {
			System.out.println("\u001B[33mError: ClassNotFound"+ e.getMessage()+"\u001B[0m");
		}
        return Optional.of(Collections.emptyList());
	}
	
	public void readQuestionFile(String fileName) {
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			
			List<Question> questions = new LinkedList<>();
			
			Question question = null;
			
			String line ;
			
			int index = 0;
			
				while (index<=5&&(line=br.readLine())!=null) {
					switch (index) {
					case 0:
						question = new Question();
						question.setNo(line);
						index++;
						break;
					case 1:
						question.setPuan(line);
						index++;
						break;
					case 2:
						question.setContent(line);
						index++;
						break;
					case 3:
						question.getOptions().put(line.substring(0, 2), line.substring(3));
						if (question.getOptions().size()==5) {
							index++;
						}
						break;
					case 4:
						question.setCorrectOption(line);
						questions.add(question);
						index=0;
						break;
				}
			}
			writeQuestions(questions);
			ExamRunner.db.questions=questions;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
