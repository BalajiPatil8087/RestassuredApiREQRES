package com.maveric.project.pojos;

public class RegisterUnSucessPojo {
	String email;

	public RegisterUnSucessPojo() {
	}

	public RegisterUnSucessPojo(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "RegisterUnSucessPojo [email=" + email + "]";
	}
}
