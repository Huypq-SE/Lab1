package fall.hsf301.slot02.repository;

import java.util.List;

import fall.hsf301.slot02.pojo.Student;

public interface IStudentRepo {
	public List<Student> getStudents();
	public void save(Student student);
	public void delete(Long studentID);
	public Student findByID(Long studentID);
	public void update(Student student);
	public List<Student> findByFirstName(String firstName);

}
