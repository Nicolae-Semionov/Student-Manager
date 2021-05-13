package StudentManager;

import java.io.*;
import java.util.*;

public class Data {

	public static Map<String, Student> load(File file) {
		Map out = new HashMap<String, Student>();
		
		int i = 0;
		int j;
		String f;
		String l;
		String n;
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				f = "";
				l = "";
				n = "";
				j = 0;
				
				for(j = 0; line.charAt(j) != ' '; j++)
					f = f + line.charAt(j);
				
				for(j = j+1; line.charAt(j) != ' '; j++)
					l = l + line.charAt(j);
				
				for(j = j+1; j < line.length(); j++)
					n = n + line.charAt(j);
				
				//System.out.println(f + ", " + l + ", " + n);
				
				out.put(n, new Student(f, l, n));
				
				i++;
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return out;
	}
	
	public static void save(Map<String, Student> Students, File file) {
		
		try {
		      FileWriter myWriter = new FileWriter(file);
		      for(Student s : Students.values()) {
		    	  myWriter.write(s.getFirst() + " " + s.getLast() + " " + s.getId() + "\n");
		      }
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
}
