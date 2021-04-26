package StudentManager;

public class Main {
	
	public static void main(String[] args) {
		Student a = new Student("Eugene", "McDonald", 216);
		Student b = new Student("Hubert", "Anderson", 218);
		System.out.println(a);
		System.out.println(b);
		System.out.println(a.compareTo(b));
	}
}
