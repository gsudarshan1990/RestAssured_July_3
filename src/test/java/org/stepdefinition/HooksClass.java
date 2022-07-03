package org.stepdefinition;

import java.io.IOException;

import org.builder.Builder;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class HooksClass {
	static RequestSpecification reqSpec;

	@Before(order=1)
	public void beforeRequest1() throws IOException {
		reqSpec = RestAssured.given().log().all().spec(Builder.getRequestSpecBuilder());
	}
	@Before(order=2,value="@updateIssue or @deleteIssue")
	public void beforeRequest2() throws IOException {
		JIRASteps j = new JIRASteps();
		j.to_give_the_and_for_the_post_request("Login not working", "Cant able to login app");
		j.to_call_the_api_with_http_request_method("createissue", "post");
	}

}
