package com.adse2509.sess01_java_util_apis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.adse2509.classes.Student;
import com.adse2509.util.Spacer;

/**
 * Java class that demonstrates some of the concrete classes that implement
 * the list interface from the java.util package and some of its methods.
 * 
 * @author Nyanjui
 */
public class ListDemo 
{
	public static void main(String[] args) 
	{
		// Create a list of fruits
		List<String> fruits = new ArrayList<>();
		
		// Add fruits to the fruit list
		fruits.add("Avocado");
		fruits.add("Watermelon");
		fruits.add("Banana");
		fruits.add("Apple");
		fruits.add("Pawpaw");
		fruits.add("Pineapple");
		fruits.add("Kiwi");
		fruits.add("Mango");
		fruits.add("Passion");
		fruits.add("Grapes");
		fruits.add("Guava");
		fruits.add("Strawberries");
		fruits.add("Orange");
		fruits.add("Lemon");
		fruits.add("Apricot");
		fruits.add("Dragon Fruit");
		fruits.add("Thorn Melon");
		fruits.add("Sweet Melon");
		fruits.add("Blueberries");
		fruits.add("Cherries");
		fruits.add("Peaches");
		fruits.add("Pomegranate");
		
		// Display the above fruits using a for...loop
		for (String fruit: fruits)
			System.out.println(fruit);
		
		// Create a list of students from the classes package
		List<Student> javaStudents= new ArrayList<>();
		
		// Enroll/add students to the Java class.
		javaStudents.add(new Student("EICN-0001", "Java Professional Programming", "Zamzam Ali",
				LocalDate.of(2004, 11, 06), 'F', "Kenyan"));
		javaStudents.add(new Student("EICN-0002", "Java Professional Programming", "Yasson Simonette",
				LocalDate.of(2006, 12, 02), 'M', "Kenyan"));
		javaStudents.add(new Student("EICN-0003", "Java Professional Programming", "Chengkuach Mathok",
				LocalDate.of(2008, 06, 06), 'M', "South Sudanese"));
		javaStudents.add(new Student("EICN-0004", "Java Professional Programming", "Mohamed Ali",
				LocalDate.of(2005, 05, 05), 'M', "Kenyan"));
		javaStudents.add(new Student("EICN-0005", "Java Professional Programming", "Michael Williams",
				LocalDate.of(2005, 12, 22), 'M', "American"));
		javaStudents.add(new Student("EICN-0006", "Java Professional Programming", "John Wanjohi",
				LocalDate.of(2005, 11, 06), 'M', "Kenyan"));
		javaStudents.add(new Student("EICN-0007", "Java Professional Programming", "Hayat Abdi",
				LocalDate.of(2006, 06, 17), 'F', "Arabian"));
		javaStudents.add(new Student("EICN-0008", "Java Professional Programming", "John Doe",
				LocalDate.of(2002, 05, 16), 'M', "American"));
		javaStudents.add(new Student("EICN-0009", "Java Professional Programming", "Abdulrahman Rahim",
				LocalDate.of(1995, 8, 01), 'M', "Kenyan"));
		javaStudents.add(new Student("EICN-0010", "Java Professional Programming", "Florence Wambua",
				LocalDate.of(2006, 8, 07), 'F', "Kenyan"));
		javaStudents.add(new Student("EICN-0011", "Java Professional Programming", "Nicholas Koech",
				LocalDate.of(2006, 04, 12), 'M', "Kenyan"));
		javaStudents.add(new Student("EICN-0012", "Java Professional Programming", "Rahma Hassan",
				LocalDate.of(2004, 05, 9), 'F', "Kenyan"));
		
		Spacer.separator();
		System.out.println("Java Professional Programming Students");
		// Display the details of the above students
		for(Student student: javaStudents)
			System.out.println(student);
		
		// 2 students drop from the course
		javaStudents.remove(1);
		javaStudents.remove(6);
		
		// Display the remaining number of students in the class
		System.out.println("After the 2nd & 7th students dropped from the class we have "
				+ javaStudents.size()+ " students left.");
		
		// Enroll 3 new students in the class
		javaStudents.add(new Student("EICN-0013", "Java Professional Programming", "Sheila Josiah",
				LocalDate.of(1993, 8, 04), 'F', "Kenyan"));
		javaStudents.add(new Student("EICN-0014", "Java Professional Programming", "Noor Gupta",
				LocalDate.of(2006, 06, 14), 'M', "Indian"));
		javaStudents.add(new Student("EICN-0015", "Java Professional Programming", "Tracy Chapman",
				LocalDate.of(2000, 10, 26), 'F', "South African"));
		
		// Display the student details using an iterator
		Spacer.separator();
		System.out.println("The final list of students is:");
		Iterator<Student> stud = javaStudents.iterator();
		int n = 0;
		while(stud.hasNext())
		{
			System.out.println("Displaying the details for student: " + (n + 1));
			System.out.println(stud.next().toString());
			n++;
		}
		
		// Display the final number of students in the class
		System.out.println("The final number of students after dropout and new "
				+ "enrollments is: " + javaStudents.size());
		
	}

}
