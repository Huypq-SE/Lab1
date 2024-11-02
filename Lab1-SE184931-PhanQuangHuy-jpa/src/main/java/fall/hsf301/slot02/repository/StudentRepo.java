package fall.hsf301.slot02.repository;

import java.util.List;
import fall.hsf301.slot02.dao.StudentDAO;
import fall.hsf301.slot02.pojo.Student;

public class StudentRepo implements IStudentRepo{
	private StudentDAO studentDAO;
	
	public StudentRepo(String jpaName) {
		studentDAO = new StudentDAO(jpaName);
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		studentDAO.save(student);
		
	}

	@Override
	public void delete(int studentID) {
		// TODO Auto-generated method stub
		studentDAO.delete(studentID);
		
	}

	@Override
	public Student findByID(int studentID) {
		// TODO Auto-generated method stub
		return studentDAO.findById(studentID);
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		studentDAO.update(student);
		
	}

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentDAO.getStudents();
	}

	@Override
	public List<Student> findByName(String studentName) {
		return studentDAO.findByName(studentName);
	}

}
