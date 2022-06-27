package res;

import java.io.IOException;

import org.base.BaseClass;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JIRAAPIRequest2 extends BaseClass {
	public static void main(String[] args) throws ParseException, IOException {
		System.out.println("============GET==============");
		// get values from property file
		RestAssured.baseURI = getPropertyFileValue("baseUriJira");
		String user = getPropertyFileValue("username");
		String pass = getPropertyFileValue("password");
		Response get = RestAssured.given().header("Content-Type", "application/json").auth().preemptive()
				.basic(user, pass).when().get("rest/api/2/search");
		System.out.println("Response Code...." + getStatusCode(get));
		System.out.println("Response Body...." + getResponseBody(get));

		System.out.println("============POST==============");
		Response post = RestAssured.given().header("Content-Type", "application/json").body(PayLoad.createIssue())
				.auth().preemptive().basic(user, pass).when().post("rest/api/2/issue/");
		System.out.println("Response Code...." + getStatusCode(post));
		System.out.println("Response Body...." + getResponseBody(post));

		// get key--api chaining
		String key = getJSONValue(getResponseBody(post), "key");
		System.out.println("============PUT==============");
		Response put = RestAssured.given().header("Content-Type", "application/json").body(PayLoad.updateIssue()).auth()
				.preemptive().basic(user, pass).when().put("rest/api/2/issue/" + key);
		System.out.println("Response Code...." + getStatusCode(put));
		System.out.println("Response Body...." + getResponseBody(put));

		System.out.println("============DELETE==============");
		Response delete = RestAssured.given().header("Content-Type", "application/json").auth().preemptive()
				.basic(user, pass).when().delete("rest/api/2/issue/" + key);
		System.out.println("Response Code...." + getStatusCode(delete));
		System.out.println("Response Body...." + getResponseBody(delete));
	}

}
