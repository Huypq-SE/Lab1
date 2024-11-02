package fall24.hsf301.slot02.gui;

import fall.hsf301.slot02.service.StudentService;

import java.util.HashSet;
import java.util.Set;

import fall.hsf301.slot02.pojo.Book;
import fall.hsf301.slot02.pojo.Student;
import fall.hsf301.slot02.service.IStudentService;

public class MainGUI {

	public static void main (String []args)
{
		String fileName = "JPAs";
		IStudentService studentService = new StudentService(fileName);
		Student student = new Student("Lam", "Nguyen", 9);
		Book book = new Book("Java Persistence with Spring", "Catalin Tudose", "9781617299186");
		student.getBooks().add(book); 
		studentService.save(student);			

}
	}
