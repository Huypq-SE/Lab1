package fall.hsf301.slot02.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "firstName", nullable = false, unique = false)
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "marks")
	private int marks;

	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Set<Book> books =  new HashSet<>();

	public Student() {

	}

	public Student(String firstName, String lastName, int marks) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.marks = marks;
	}
	
	

	public Student(int id, String firstName, String lastName, int marks) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.marks = marks;
	}
	
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
}
