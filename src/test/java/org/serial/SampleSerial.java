package org.serial;

import java.io.IOException;

import org.base.BaseClass;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import res.PayLoad;

public class SampleSerial extends BaseClass {
	public static void main(String[] args) throws IOException, ParseException {
		RestAssured.baseURI = getPropertyFileValue("baseUri");
		System.out.println("============POST=============");
//		serialization
		CreateUser user= new CreateUser();
		user.setName("Manoj");
		user.setJob("Team Leader");
		
		
		Response post = RestAssured.given().header("Content-Type", "application/json").body(user)
				.post("api/users");

		System.out.println("Response Code...." + getStatusCode(post));
		System.out.println("Response Body...." + getResponseBody(post));

//	deserialization
		ReadUser us = post.as(ReadUser.class);
		System.out.println(us.getName());
		System.out.println(us.getJob());
		System.out.println(us.getId());
		System.out.println(us.getCreatedAt());

		System.out.println("============PUT=============");
		CreateUser update= new CreateUser();
		update.setName("Manoj");
		update.setJob("Manager");
		Response put = RestAssured.given().header("Content-Type", "application/json").body(update)
				.put("api/users/2");
		System.out.println("Response Code...." + getStatusCode(put));
		System.out.println("Response Body...." + getResponseBody(put));
	}

}
