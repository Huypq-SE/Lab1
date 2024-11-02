package fall.hsf301.slot02.pojo;


import java.util.HashSet;
import java.util.Objects;
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
@Table(name="students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "mark")
	private int mark;
	
	@OneToMany(cascade =CascadeType.ALL)
	@JoinColumn(name="student_id")
	private Set<Book> books  = new HashSet<Book>();
	public Student(String firstName, String lastName, int mark) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mark = mark;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public Student( String firstName, String lastName, int mark, Set<Book> books) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.mark = mark;
		this.books = books;
	}
	public Student() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(Id, firstName, lastName, mark);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& mark == other.mark;
	}
	@Override
	public String toString() {
		return "Student [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", mark=" + mark + "]";
	}

	
}
