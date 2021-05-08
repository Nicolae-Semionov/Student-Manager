package StudentManager;

public class Student implements Comparable<Student>{
	private String first;
	private String last;
	private String name;
	private int id;
	
	public Student(String f, String l, int num) {
		first = f;
		last = l;
		name = f + " " + l;
		id = num;
	}
	
	public Student(String f, String l, String num) {
		first = f;
		last = l;
		name = f + " " + l;
		
		for(int i = 0; i < num.length(); i++) {
			id += (num.charAt(num.length() - i - 1) - 48) * Math.pow(10, i);
		}
			
	}
	
	public String getFirst() {
		return first;
	}
	
	public String getLast() {
		return last;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String toString() {
		return first + " " + last + " #" + id;
	}

	@Override
	public int compareTo(Student o) {
		if(this.id < o.id) 
			return -1;
		else if(this.id > o.id) 
			return 1;
		else
			return 0;
	}
}
