package fall.hsf301.slot02.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import fall.hsf301.slot02.pojo.Student;

public class StudentDAO {
	private	static EntityManager em;
	private static EntityManagerFactory emf;
	public StudentDAO(String persistanceName)
	{
		emf = Persistence.createEntityManagerFactory(persistanceName);
	}
	public void save(Student student) {
		try {
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(student);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error"+e.getMessage());
		}finally {
			em.close();
		}
	}
	
	public List<Student> getStudents(){
		List<Student> students	= null;
		try {
			em= emf.createEntityManager();
			em.getTransaction().begin();
			students = em.createQuery("from Student").getResultList();
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}finally {em.close();}
		return students;
	};
	
	public void delete(int studentID) {
		try {
			em= emf.createEntityManager();
			em.getTransaction().begin();
			Student s=em.find(Student.class, studentID);
			em.remove(s);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}finally {em.close();}
		
	}
	
	public Student findById(int studentID) {
		Student student=null;
		try {
			em =emf.createEntityManager();
			em.getTransaction().begin();
			student =em.find(Student.class, studentID);
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		} finally {em.close();}
		return student;
		}
	
	public List<Student> findByName(String studentName) {
	    List<Student> students = null;
	    try {
	        em = emf.createEntityManager();
	        em.getTransaction().begin();
	        students = em.createQuery("SELECT s FROM Student s WHERE s.firstName LIKE :firstName", Student.class)
	                .setParameter("firstName", "%" + studentName + "%")  
	                .getResultList();
	        em.getTransaction().commit(); 
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); 
	        }
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close(); 
	        }
	    }
	    return students;
	}
	
	public void update(Student student) {
		try {
			em= emf.createEntityManager();
			em.getTransaction().begin();
			Student s =em.find(Student.class, student.getId());
			if(s != null) {
				s.setFirstName(student.getFirstName());
				s.setLastName(student.getLastName());
				s.setMarks(student.getMarks());
				em.getTransaction().commit();
			}
			
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}finally {em.close();}
		
	}
	

}
