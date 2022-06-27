package org.base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.response.Response;

public class BaseClass {
	public static int getStatusCode(Response res) {
		return res.getStatusCode();
	}
	public static String getResponseBody(Response res) {
		return res.getBody().asString();
	}
	public static String getJSONValue(String body,String key) throws ParseException {
		JSONParser js= new JSONParser();
		Object obj = js.parse(body);
		JSONObject jb=(JSONObject)obj;
		return jb.get(key).toString();
	}
	public static String getPropertyFileValue(String key) throws IOException {
		Properties p= new Properties();
		FileReader f= new FileReader("C:\\Users\\linga\\eclipse-workspace\\RestAssured\\src\\test\\resources\\PropertyFiles\\config.properties");
		p.load(f);
		return p.get(key).toString();
	}
	// public static void main(String[] args) throws IOException {
	// Properties p= new Properties();
	// FileReader f= new
	// FileReader("C:\\Users\\linga\\eclipse-workspace\\RestAssured\\src\\test\\resources\\PropertyFiles\\config.properties");
	// p.load(f);
	// Object ob = p.get("baseUri");
	// String st = ob.toString();
	// System.out.println(st);
	// }
}
