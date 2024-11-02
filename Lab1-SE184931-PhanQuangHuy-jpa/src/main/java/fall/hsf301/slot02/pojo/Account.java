package fall.hsf301.slot02.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNTS")
public class Account {
	@Id
	@Column(name = "userName")
	private String userName;
	@Column(name = "passWord")
	private String passWord;
	@Column(name ="role")
	private String role;

	public Account(String userName, String passWord, String role) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}
	

	public Account() {
		super();
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
