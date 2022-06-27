package org.deserial;

import java.io.IOException;
import java.util.List;

import org.base.BaseClass;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import res.PayLoad;

public class SampleDeSerial extends BaseClass {

	public static void main(String[] args) throws IOException, ParseException {
		RestAssured.baseURI = getPropertyFileValue("baseUri");

		System.out.println("============GET=============");
//		Response re = RestAssured.given().header("Content-Type", "application/json").queryParam("page", "2").when()
//				.get("api/users");
//		System.out.println("Response Code...." + getStatusCode(re));
//		System.out.println("Response Body...." + getResponseBody(re));

//		UserDatas user = re.as(UserDatas.class);
		
		
		
		UserDatas user = RestAssured.given().header("Content-Type", "application/json").queryParam("page", "2").when()
				.get("api/users").as(UserDatas.class);

		int page = user.getPage();
		System.out.println("Page......" + page);
		System.out.println("Per Page...." + user.getPer_page());
		System.out.println("toatl...." + user.getTotal());
		System.out.println("Toatal Page...." + user.getTotal_pages());

		List<Data> data = user.getData();

		Data d1 = data.get(1);
		System.out.println("Id...." + d1.getId());
		System.out.println("email...." + d1.getEmail());
		System.out.println("First name...." + d1.getFirst_name());
		System.out.println("Last name...." + d1.getLast_name());
		System.out.println("Avater...." + d1.getAvatar());
		System.out.println("-----------------------------------------------");
		for (Data x : data) {

			System.out.println("Id...." + x.getId());
			System.out.println("email...." + x.getEmail());
			System.out.println("First name...." + x.getFirst_name());
			System.out.println("Last name...." + x.getLast_name());
			System.out.println("Avater...." + x.getAvatar());
			System.out.println();
		}

		System.out.println("-----Support------------");
		Support support = user.getSupport();
		System.out.println("Url....." + support.getUrl());
		System.out.println("Text....." + support.getText());

		
	}
}
