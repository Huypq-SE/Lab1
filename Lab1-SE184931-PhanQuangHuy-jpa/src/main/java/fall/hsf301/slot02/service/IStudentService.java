package fall.hsf301.slot02.service;

import java.util.List;

import fall.hsf301.slot02.pojo.Student;

public interface IStudentService {
	public List<Student> getStudents();
	public void save(Student student);
	public void delete(int studentID);
	public Student findByID(int studentID);
	public void update(Student student);
	public Student readInformation();
	public List<Student> findByName(String studentName);

}
