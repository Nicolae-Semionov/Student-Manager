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
