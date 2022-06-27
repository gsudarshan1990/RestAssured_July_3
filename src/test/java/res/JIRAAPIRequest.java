package res;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JIRAAPIRequest {
	public static void main(String[] args) {
		System.out.println("============GET==============");
		RestAssured.baseURI = "https://swiggyy.atlassian.net/";
		Response get = RestAssured.given().header("Content-Type", "application/json").auth().preemptive()
				.basic("sekarmano46@gmail.com", "AUTHf6YCMCmEHarE1Sg73CE0").when().get("rest/api/2/search");

		System.out.println("Response code......" + get.getStatusCode());
		System.out.println("-----Response Bode----");
		System.out.println(get.getBody().asString());
		System.out.println("Response code......" + get.getStatusCode());

		System.out.println("============POST==============");
		Response post = RestAssured.given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"fields\": {\r\n" + "       \"project\":\r\n" + "       {\r\n"
						+ "          \"key\": \"SG\"\r\n" + "       },\r\n"
						+ "       \"summary\": \"Login Not working in Swiggy\",\r\n"
						+ "       \"description\": \"User cant able to login in app\",\r\n"
						+ "       \"issuetype\": {\r\n" + "          \"name\": \"Bug\"\r\n" + "       }\r\n"
						+ "   }\r\n" + "}")
				.auth().preemptive().basic("sekarmano46@gmail.com", "AUTHf6YCMCmEHarE1Sg73CE0").when()
				.post("rest/api/2/issue/");

		System.out.println("Response code......" + post.getStatusCode());
		System.out.println("-----Response Bode----");
		System.out.println(post.getBody().asString());

		System.out.println("============PUT==============");
		Response put = RestAssured.given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"fields\" : {\r\n" + "        \"summary\": \"Login in Swiggy not working\",\r\n"
						+ "        \"description\": \"user cant login with mobile number\"\r\n" + "    }\r\n" + "}")
				.auth().preemptive().basic("sekarmano46@gmail.com", "AUTHf6YCMCmEHarE1Sg73CE0").when()
				.put("rest/api/2/issue/SG-315");

		System.out.println("Response code......" + put.getStatusCode());
		System.out.println("-----Response Bode----");
		System.out.println(put.getBody().asString());

		System.out.println("============DELETE==============");
		Response delete = RestAssured.given().header("Content-Type", "application/json").auth().preemptive()
				.basic("sekarmano46@gmail.com", "AUTHf6YCMCmEHarE1Sg73CE0").when().delete("rest/api/2/issue/SG-315");

		System.out.println("Response code......" + delete.getStatusCode());
		System.out.println("-----Response Bode----");
		System.out.println(delete.getBody());
	}

}
