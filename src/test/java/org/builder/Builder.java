package org.builder;

import java.io.IOException;
import java.io.PrintStream;

import org.base.BaseClass;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Builder extends BaseClass {
	static PrintStream p;

	public static RequestSpecification getRequestSpecBuilder() throws IOException {
		if (p == null) {
			p = new PrintStream("log.txt");
		}

		RequestSpecBuilder req = new RequestSpecBuilder();

		PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
		auth.setUserName(getPropertyFileValue("username"));
		auth.setPassword(getPropertyFileValue("password"));

		RequestSpecification reqSpec = req.setBaseUri(getPropertyFileValue("baseUriJira")).setAuth(auth)
				.addFilter(RequestLoggingFilter.logRequestTo(p)).addFilter(ResponseLoggingFilter.logResponseTo(p))
				.setContentType(ContentType.JSON).build();
		return reqSpec;

	}

	public static ResponseSpecification getResponseSpecBuilder(int code) {
		ResponseSpecBuilder res = new ResponseSpecBuilder();
		ResponseSpecification resSpec = res.expectContentType(ContentType.JSON).expectStatusCode(code).build();
		return resSpec;

	}
}
