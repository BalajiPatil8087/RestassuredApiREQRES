package com.maveric.project.pojos;
//In RestAssured, a POJO (Plain Old Java Object) class is used to map JSON  responses to Java objects.
public class UserPojo {
	String name, job;

	public UserPojo() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "UserPojo [name=" + name + ", job=" + job + "]";
	}

}
