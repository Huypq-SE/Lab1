package fall.hsf301.slot02.service;

import java.util.List;
import java.util.Scanner;

import fall.hsf301.slot02.pojo.Student;
import fall.hsf301.slot02.repository.IStudentRepo;
import fall.hsf301.slot02.repository.StudentRepo;

public class StudentService implements IStudentService{
	private IStudentRepo studentRepo;
	
	public StudentService(String jpaName) {
		
		studentRepo = new StudentRepo(jpaName);
	}

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentRepo.getStudents();
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		studentRepo.save(student);
		
	}

	@Override
	public void delete(int studentID) {
		// TODO Auto-generated method stub
		studentRepo.delete(studentID);
		
	}

	@Override
	public Student findByID(int studentID) {
		// TODO Auto-generated method stub
		return studentRepo.findByID(studentID);
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
	   studentRepo.update(student);	  		
	}

	@Override
	public Student readInformation() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("First Name: ");
		String firstName = sc.nextLine();
		System.out.println("Last Name: ");
		String lastName = sc.nextLine();
		System.out.println("Marks: ");
		int mark =sc.nextInt();
		return new Student(firstName,lastName,mark);
	}

	@Override
	public List<Student> findByName(String studentName) {
		return studentRepo.findByName(studentName);
	}

}
