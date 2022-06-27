package res;

import java.io.IOException;

import org.base.BaseClass;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class SampleRequest extends BaseClass {
	public static void main(String[] args) throws ParseException, IOException {
		RestAssured.baseURI = getPropertyFileValue("baseUri");

		System.out.println("============GET=============");
		Response re = RestAssured.given().header("Content-Type", "application/json").queryParam("page", "2").when()
				.get("api/users");
		System.out.println("Response Code...." + getStatusCode(re));
		System.out.println("Response Body...." + getResponseBody(re));

		System.out.println("============POST=============");
		Response post = RestAssured.given().header("Content-Type", "application/json").body(PayLoad.createUser())
				.post("api/users");

		System.out.println("Response Code...." + getStatusCode(post));
		System.out.println("Response Body...." + getResponseBody(post));
		
//		JSONParser js= new JSONParser();
//		Object obj = js.parse(getResponseBody(post));
//		JSONObject jb=(JSONObject)obj;
//		String name = jb.get("name").toString();
		String name = getJSONValue(getResponseBody(post), "name");
		System.out.println(name);
		System.out.println(getJSONValue(getResponseBody(post), "job"));

		System.out.println("============PUT=============");
		Response put = RestAssured.given().header("Content-Type", "application/json").body(PayLoad.updateUser(name))
				.put("api/users/2");
		System.out.println("Response Code...." + getStatusCode(put));
		System.out.println("Response Body...." + getResponseBody(put));

		System.out.println("============DELETE=============");
		Response delete = RestAssured.given().header("Content-Type", "application/json").delete("api/users/2");
		System.out.println("Response Code...." + getStatusCode(delete));
		System.out.println("Response Body...." + getResponseBody(delete));	}}
