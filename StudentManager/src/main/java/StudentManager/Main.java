package StudentManager;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.*;

public class Main {
	
	public static void main(String[] args) {
		
		ArrayList Students = Data.load("C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
		
		for(int i = 0; i < Students.size(); i++) {
			System.out.println(Students.get(i));
		}
		
		Students.add(new Student("Jimmy", "Jones", 345));
		Students.add(new Student("Jeff", "Valantine", 355));
		
		Data.save(Students, "C:/Users/nicol/git/StudentManager/StudentManager/test.txt");

	}
}
