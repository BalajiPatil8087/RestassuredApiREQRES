package com.maveric.project.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.project.pojos.LoginPojo;
import com.maveric.project.pojos.RegisterPojo;
import com.maveric.project.pojos.RegisterUnSucessPojo;
import com.maveric.project.pojos.User;
import com.maveric.project.pojos.UserPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAPITest {
	@BeforeClass
	public static void setUpEnv() {
		RestAssured.baseURI = "https://reqres.in/";
	}

	

	@Test(testName = "Get all List Users")
	public void test1() {
		String responseBody = RestAssured.get("/api/users?page=2").then().assertThat().statusCode(200).log().body()
				.toString();
		System.out.println(responseBody);
	}

	@Test(testName = "Get Single Users")
	public void test2() {
		String responseBody = RestAssured.get("/api/users/2").then().assertThat().statusCode(200).log().body()
				.toString();
		System.out.println(responseBody);
	}

	@Test(testName = "SINGLE USER NOT FOUND")
	public void test3() {
		String responseBody = RestAssured.get("/api/users/23").then().assertThat().statusCode(404).log().body()
				.toString();
		System.out.println(responseBody);
	}

	@Test(testName = "LIST <RESOURCE>")
	public void test4() {
		String responseBody = RestAssured.get("/api/unknown").then().assertThat().statusCode(200).log().body()
				.toString();
		System.out.println(responseBody);
	}

	@Test(testName = "SINGLE <RESOURCE>")
	public void test5() {
		String responseBody = RestAssured.get("/api/unknown/2").then().assertThat().statusCode(200).log().body()
				.toString();
		System.out.println(responseBody);
	}

	@Test(testName = "SINGLE <RESOURCE> NOT FOUND")
	public void test6() {
		String responseBody = RestAssured.get("/api/unknown/23").then().assertThat().statusCode(404).log().body()
				.toString();
		System.out.println(responseBody);
	}
  //POST
	
	@Test(testName = "Add new User Details using UserData.json file")
	public void test7() throws StreamReadException, DatabindException, IOException   {
       
		ObjectMapper mapper = new ObjectMapper();
		
		FileInputStream stream = new FileInputStream(new File("./TestData/UserData.json"));
		UserPojo payLoad = mapper.readValue(stream,UserPojo.class);
		
		RestAssured.given()
		.contentType(ContentType.JSON)
		.body(payLoad)
		.post("/api/users")
		.then()
		.assertThat().statusCode(201).log().body();

	}
	
	//put 
	@Test(testName="update User details")
	public void test8() {
		  User updatedUser = new User("morpheus","zion resident");
		
		 RestAssured.given()
		.contentType(ContentType.JSON)
		.body(updatedUser)
		.put("/api/users/2")
		.then()
		.assertThat().statusCode(200).log().body();

		
	}
	//Patch
	@Test(testName="update User details")
	public void test9() {
		 User updatedUser = new User("morpheus","zion resident");
			
		 RestAssured.given()
		.contentType(ContentType.JSON)
		.body(updatedUser)
		.patch("/api/users/2")
		.then()
		.assertThat().statusCode(200).log().body();

	}
	//Delete
	@Test(testName="Delete User details")
	public void test10() {
		
		 RestAssured.given()
		.contentType(ContentType.JSON)
		.delete("/api/users/2")
		.then()
		.assertThat().statusCode(204).log().body();

	}
	
	//Post Register
	@Test(testName = "Register User using RegisterData.json file")
	public void test11() throws StreamReadException, DatabindException, IOException   {
       
		ObjectMapper mapper = new ObjectMapper();
		
		FileInputStream stream = new FileInputStream(new File("./TestData/RegisterData.json"));
		RegisterPojo payLoad = mapper.readValue(stream,RegisterPojo.class);
		
		RestAssured.given()
		.contentType(ContentType.JSON)
		.body(payLoad)
		.post("/api/register")
		.then()
		.assertThat().statusCode(200).log().body();
		

	}
	//Post Register unsuccessful
		@Test(testName = "Register- UNSUCCESSFUL User using RegisterDataUnSucess.json file")
		public void test12() throws StreamReadException, DatabindException, IOException   {
	       
			ObjectMapper mapper = new ObjectMapper();
			
			FileInputStream stream = new FileInputStream(new File("./TestData/RegisterDataUnSucess.json"));
			RegisterUnSucessPojo payLoad = mapper.readValue(stream,RegisterUnSucessPojo.class);
			
			RestAssured.given()
			.contentType(ContentType.JSON)
			.body(payLoad)
			.post("/api/register")
			.then()
			.assertThat().statusCode(400).log().body();
			

		}
		
		//Post Login successful
				@Test(testName = "Login User using RegisterDataUnSucess.json file")
				public void test13() throws StreamReadException, DatabindException, IOException   {
			       
					ObjectMapper mapper = new ObjectMapper();
					
					FileInputStream stream = new FileInputStream(new File("./TestData/LoginData.json"));
					LoginPojo payLoad = mapper.readValue(stream,LoginPojo.class);
					
					RestAssured.given()
					.contentType(ContentType.JSON)
					.body(payLoad)
					.post("/api/login")
					.then()
					.assertThat().statusCode(200).log().body();
					

				}
			
	
	
	
	@AfterClass
	public static void tearDownEnv() {
		RestAssured.reset();
	}
}
