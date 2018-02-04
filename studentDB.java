import java.util.*;

public class studentDBApp {

	public static void main(String[] args) {
		new Course("biology", 1000);
		new Course("physics", 500);
		new Course("compScience", 3000);
		new Course("art", 3000);


		Student s1 = new Student("eli", "145784323");
		s1.setCityAndState("brooklyn", "new york");
		s1.setPhone("6465767689");
//		s1.printToString();
		s1.addCourse(1);
		s1.addCourse(3);
		s1.addCourse(4);
		s1.checkBalance();
		s1.payAndRemoveCourse(1);

	}

}

class Student{
	public static int ID = 1;
	public static ArrayList<Object> all = new ArrayList<Object>();
	private String name;
	private String ssn;
	private ArrayList<Integer> courses = new ArrayList<Integer>();
	private String email;
	private double balance;
	private boolean enrolled = false;
	private int id;
	private String studentID;
	private String phone;
	private String state;
	private String city;

	Student(String name, String ssn){
		this.name = name;
		this.ssn = ssn;
		this.id = Student.ID++;
		this.generateEmail();
		this.generateStudentID();
		Student.all.add(this);

	}

	public void setCityAndState(String city, String state) {
		this.city = city;
		this.state = state;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private void generateStudentID() {
		int rand = (int)(Math.random() * ((9000 - 1000) +1 ));
		this.studentID = Integer.toString(this.id) + Integer.toString(rand) + this.ssn.substring(0, 4);
	}

	private void generateEmail(){
		this.email = this.name + "@school.com";
	}

	public void enroll() {
		this.enrolled = !this.enrolled;
	}

	public void checkBalance() {
		System.out.println("[Balance $" + this.balance + "]");
	}

	public void payAndRemoveCourse(int courseID) {
		for (int i = 0; i < this.courses.size(); i++) {
			if (this.courses.get(i) == courseID) {
				Course tempCourse = (Course) Course.courses.get(courseID);
				this.balance -= tempCourse.amount;
				this.courses.remove(i);
				System.out.println("you just paid off your course: " + tempCourse.name);
				this.courses();
				System.out.println("\n");
				this.checkBalance();
			}
		}
	}

	private void pay(double amount) {
		this.balance -= amount;
		if (!((this.balance - (amount)) < 0)) {
			System.out.println("you just paid " + amount);
			this.checkBalance();
		} else {
			int change = (int) (amount - this.balance);
			this.balance = 0;
			System.out.println("Your change is $" + change + " your balance is $0");
		}
	}


	public void addCourse(int courseID) {
		if (Course.courses.get(courseID) != null) {
			this.courses.add(courseID);
			Course course = (Course) Course.courses.get(courseID);
			this.balance += course.amount;
		} else {
			System.err.println("That course is not in our system");
		}
	}
	@Override
	public String toString() {
		return "[" + this.studentID + " Name: " + this.name + " SSN: " + this.ssn + " Email " + this.email + " Phone: " + this.phone  +"]";
	}

	public void printToString() {
		System.out.println(this.toString());
	}

	public void courses() {
		for (int i = 0; i < this.courses.size(); i++) {
			Course tempCourse = (Course) Course.courses.get(this.courses.get(i));
			System.out.println("Course: " + tempCourse.name + ". Price $" + tempCourse.amount);
		}
	}

}

class Course{
	public static int ID = 1;
	public String name;
	public int amount;
	public int id;
	public static Map<Integer, Object> courses = new HashMap<Integer, Object>();


	Course(String name, int amount){
		this.name = name;
		this.amount = amount;
		this.id = Course.ID++;
		Course.courses.put(id, this);
	}



}
