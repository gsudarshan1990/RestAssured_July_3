package org.asserts;

import java.io.IOException;

import org.base.BaseClass;
import org.builder.Builder;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.serial.ReadIssue;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import res.PayLoad;

public class JiraAssert extends BaseClass {
	static String key;

	@Test
	public void test1() throws IOException {
		System.out.println("=============GET=============");

		//// Request
		// RequestSpecBuilder req=new RequestSpecBuilder();
		//
		//
		// PreemptiveBasicAuthScheme auth=new PreemptiveBasicAuthScheme();
		// auth.setUserName(getPropertyFileValue("username"));
		// auth.setPassword(getPropertyFileValue("password"));
		//
		// RequestSpecification reqSpec =
		//// req.setBaseUri(getPropertyFileValue("baseUriJira")).setAuth(auth).setContentType(ContentType.JSON).build();
		//
		//
		//// Response
		// ResponseSpecBuilder res= new ResponseSpecBuilder();
		// ResponseSpecification resSpec =
		//// res.expectContentType(ContentType.JSON).expectStatusCode(200).build();
		//

		Response get = RestAssured.given().log().all().spec(Builder.getRequestSpecBuilder()).when()
				.get("rest/api/2/search").then().log().all().spec(Builder.getResponseSpecBuilder(200)).extract()
				.response();

		System.out.println("-----Response Code----");
		System.out.println(getResponseBody(get));
		System.out.println("Response code......" + getStatusCode(get));

		Assert.assertEquals("Ststus code should be 200", 200, getStatusCode(get));

		// then
		get.then().assertThat().statusCode(200);

	}

	@Test
	public void test2() throws IOException {
		System.out.println("============POST==============");
		Response post = RestAssured.given().log().all().spec(Builder.getRequestSpecBuilder()).body(PayLoad.issueCreate()).when()
				.post("rest/api/2/issue/").then().log().all().spec(Builder.getResponseSpecBuilder(201)).extract()
				.response();
		System.out.println("Response Code...." + getStatusCode(post));
		System.out.println("Response Body...." + getResponseBody(post));

		// -------------------Deserialization------------
		ReadIssue read = post.as(ReadIssue.class);
		System.out.println("Id.........." + read.getId());
		System.out.println("Key.........." + read.getKey());
		System.out.println("Self.........." + read.getSelf());

		// get key--api chaining
		key = read.getKey();

		Assert.assertEquals("Ststus code should be 201", 201, getStatusCode(post));
		Assert.assertTrue(read.getSelf().contains("swiggyy"));
	}

	@Test
	public void test3() throws IOException {
		System.out.println("============PUT==============");

		Response put = RestAssured.given().log().all().spec(Builder.getRequestSpecBuilder()).body(PayLoad.issueUpdate()).when()
				.put("rest/api/2/issue/" + key).then().log().all().spec(Builder.getResponseSpecBuilder(204)).extract()
				.response();
		System.out.println("Response Code...." + getStatusCode(put));
		System.out.println("Response Body...." + getResponseBody(put));
		Assert.assertEquals("Ststus code should be 204", 204, getStatusCode(put));
	}

	@Test
	public void test4() throws ParseException, IOException {

		System.out.println("============DELETE==============");
		Response delete = RestAssured.given().log().all().spec(Builder.getRequestSpecBuilder()).when()
				.delete("rest/api/2/issue/" + key).then().log().all().spec(Builder.getResponseSpecBuilder(204)).extract()
				.response();

		System.out.println("Response code......" + getStatusCode(delete));
		System.out.println("Response Body......"+getResponseBody(delete));
		Assert.assertEquals("Ststus code should be 204", 204, getStatusCode(delete));
	}
}
