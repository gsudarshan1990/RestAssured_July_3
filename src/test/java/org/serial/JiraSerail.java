package org.serial;

import java.io.IOException;

import org.base.BaseClass;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import res.PayLoad;

public class JiraSerail extends BaseClass {
	public static void main(String[] args) throws ParseException, IOException {
		RestAssured.baseURI = getPropertyFileValue("baseUriJira");
		String user = getPropertyFileValue("username");
		String pass = getPropertyFileValue("password");
//		serialization
		Project project= new  Project();
		project.setKey("SG");
		IssueType issuetype= new  IssueType();
		issuetype.setName("Bug");
		
		Fields fields = new Fields();
		fields.setProject(project);
		fields.setSummary("Payment Not Done");
		fields.setDescription("Credit card not working");
		fields.setIssuetype(issuetype);
		
		CreateIssue createIssue= new CreateIssue();
		createIssue.setFields(fields);
		
		
		System.out.println("============POST==============");
		Response post = RestAssured.given().header("Content-Type", "application/json").body(createIssue)
				.auth().preemptive().basic(user, pass).when().post("rest/api/2/issue/");
		System.out.println("Response Code...." + getStatusCode(post));
		System.out.println("Response Body...." + getResponseBody(post));
		
//		get particular details
//		-------------------Deserialization------------
		ReadIssue read = post.as(ReadIssue.class);
		System.out.println("Id.........."+read.getId());
		System.out.println("Key.........."+read.getKey());
		System.out.println("Self.........."+read.getSelf());

		// get key--api chaining
		String key = read.getKey();
		System.out.println("============PUT==============");
		
		UpdateIssueFields issueFileds= new UpdateIssueFields();
		issueFileds.setSummary("Payment still not working");
		issueFileds.setDescription("Debit card cant done");
		
		UpdateRoot rd= new UpdateRoot();
		rd.setFields(issueFileds);
		
		
		
		Response put = RestAssured.given().header("Content-Type", "application/json").body(rd).auth()
				.preemptive().basic(user, pass).when().put("rest/api/2/issue/" + key);
		System.out.println("Response Code...." + getStatusCode(put));
		System.out.println("Response Body...." + getResponseBody(put));

	}

}
