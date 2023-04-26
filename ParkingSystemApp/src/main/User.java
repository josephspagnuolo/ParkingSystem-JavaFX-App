package main;

public class User {
	
	public String firstName;
	public String lastName;
	public String email;
	public String password;
	
	public User(String first, String last, String email, String password) {
		super();
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.password = password;
	}
	
	public User(){
		super();
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [first name = " + firstName + ", last name = " + lastName + ", email = " + email + ", password = " + password + "]";
	}
}
