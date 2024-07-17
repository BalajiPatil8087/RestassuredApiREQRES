package com.maveric.project.pojos;
//In RestAssured, a POJO (Plain Old Java Object) class is used to map JSON  responses to Java objects.
public class LoginPojo {
	String email, password;

	public LoginPojo() {
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
		return "LoginPojo [email=" + email + ", password=" + password + "]";
	}

	public LoginPojo(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
}
