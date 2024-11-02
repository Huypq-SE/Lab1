package fall.hsf301.slot02.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	};
	public List<Student> getStudents(){
		List<Student> students	=null;
		try {
			em= emf.createEntityManager();
			em.getTransaction().begin();
			students = em.createQuery("from Student").getResultList();
			
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}finally {em.close();}
		return students;
	};
	public void delete(Long studentID) {
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
	public Student findById(Long studentID) {
		Student student=null;
		try {
			em =emf.createEntityManager();
			em.getTransaction().begin();
			student =em.find(Student.class, studentID);
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		} finally {em.close();}
		return student;}
	public void update(Student student) {
		try {
			em= emf.createEntityManager();
			em.getTransaction().begin();
			Student s =em.find(Student.class, student.getId());
			if(s != null) {
				s.setFirstName(student.getFirstName());
				s.setLastName(student.getLastName());
				s.setMark(student.getMark());
				em.getTransaction().commit();
			}
			
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}finally {em.close();}
		
	};
	public List<Student> findByFirstName(String firstName) {
	    List<Student> students = new ArrayList();
	    EntityManager em = null;
	    try {
	        em = emf.createEntityManager();
	        em.getTransaction().begin();
	        
	        // Use JPQL to find students by first name
	        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.firstName LIKE :firstName", Student.class);
	        query.setParameter("firstName", "%" + firstName + "%");
	        students = query.getResultList(); // Get all matching students
	        
	        em.getTransaction().commit();
	    } catch (Exception ex) {
	        System.out.println("Error: " + ex.getMessage());
	        if (em != null && em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    return students; // Return the list of matching students
	}
	

}
