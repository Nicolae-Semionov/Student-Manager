package StudentManager;

public class Student implements Comparable<Student>{
	private String first;
	private String last;
	private int id;
	
	public Student(String f, String l, int num) {
		first = f;
		last = l;
		id = num;
	}
	
	public Student(String f, String l, String num) {
		first = f;
		last = l;
		
		for(int i = 0; i < num.length(); i++) {
			id += (num.charAt(num.length() - i - 1) - 48) * Math.pow(10, i);
		}
			
	}
	
	public String getFirst() {
		String out = first;
		return out;
	}
	
	public String getLast() {
		String out = last;
		return out;
	}
	
	public String getID() {
		String out = "" + id;
		return out;
	}
	
	public String name() {
		return first + " " + last;
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
