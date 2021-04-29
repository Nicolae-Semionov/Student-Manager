package StudentManager;

import java.io.*;
import java.util.*;

public class Data {

	public static ArrayList<Student> load(String path) {
		ArrayList out = new ArrayList<Student>();
		
		int i = 0;
		int j;
		String f;
		String l;
		String n;
		
		BufferedReader reader;
		try {
			File file = new File(path);
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
				
				out.add(new Student(f, l, n));
				
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
	
	public static void save(ArrayList<Student> Students, String Path) {
		
		try {
		      FileWriter myWriter = new FileWriter(Path);
		      for(int i = 0; i < Students.size(); i++) {
		    	  Student s = Students.get(i);
		    	  myWriter.write(s.getFirst() + " " + s.getLast() + " " + s.getID() + "\n");
		      }
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
}
