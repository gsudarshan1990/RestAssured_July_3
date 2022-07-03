package org.stepdefinition;

import java.io.IOException;

import org.base.BaseClass;
import org.builder.Builder;
import org.junit.Assert;
import org.jvnet.staxex.StAxSOAPBody.Payload;
import org.serial.CreateIssue;
import org.serial.ReadIssue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import res.PayLoad;

public class JIRASteps extends BaseClass {
	RequestSpecification reqSpec = HooksClass.reqSpec;

	Response response;
	static String key;
	ReadIssue is;

	// POST
	@Given("The user should add the baseuri")
	public void the_user_should_add_the_baseuri() throws IOException {

	}

	@Given("To give the {string} and {string} for the post request")
	public void to_give_the_and_for_the_post_request(String sum, String des) throws IOException {
		reqSpec = reqSpec.body(PayLoad.issueCreate(sum, des));
	}

	@When("To call the {string} api with {string} http request method")
	public void to_call_the_api_with_http_request_method(String rs, String reqType) {
		APIRequest res = APIRequest.valueOf(rs);
		String resource = res.getResources();
		if(reqType.equalsIgnoreCase("post")) {
			response = reqSpec.when().post(resource);
			is = response.as(ReadIssue.class);
			key = is.getKey();
		}
		else if(reqType.equalsIgnoreCase("put")) {
			response = reqSpec.when().put(resource+ key);

		}
		else if(reqType.equalsIgnoreCase("get")) {
			response = reqSpec.when().get(resource);
			
		}
		else if(reqType.equalsIgnoreCase("delete")) {
			response = reqSpec.when().delete(resource+ key);
			
		}
		else {
			throw new NullPointerException("There is no Resources");
		}

	}

	

	@Then("The status code should be {int}")
	public void the_status_code_should_be(int code) {
		response = response.then().log().all().spec(Builder.getResponseSpecBuilder(code)).extract().response();

		Assert.assertEquals("Status code should be 200", code, getStatusCode(response));
	}

	@Then("The response body self contains the name {string}")
	public void the_response_body_self_contains_the_name(String expectedValue) {
		Assert.assertTrue("Self should contain swiggyy", is.getSelf().contains(expectedValue));

	}

	// GET

	

	// PUT
	@Given("The user should update {string} and {string} for the update issue")
	public void the_user_should_update_and_for_the_update_issue(String sum, String des) {
		reqSpec = reqSpec.body(PayLoad.issueUpdate(sum, des));

	}

	

	// DELET


}
