package res;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Rest {
public static void main(String[] args) {
//RequestSpecification r = RestAssured.given().header("Content-Type","application?json");
//Response res = r.when().get("https://reqres.in/api/users/");
//ResponseBody b = res.getBody();
//System.out.println(b.asString());
//int statusCode = res.getStatusCode();
//System.out.println(statusCode);

RestAssured.baseURI="https://reqres.in/";
Response res = RestAssured.given().header("Content-Type","application?json").queryParam("page", "2").get("api/users/");
ResponseBody b = res.getBody();
System.out.println(b.asString());
System.out.println(res.getStatusCode());

}
}
