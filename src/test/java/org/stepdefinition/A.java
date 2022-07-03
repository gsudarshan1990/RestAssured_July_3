package org.stepdefinition;

public class A {
	
	public static void main(String[] args) {
		APIRequest cr = APIRequest.createissue;
		System.out.println(cr.getResources());
		
		APIRequest api = APIRequest.valueOf("createissue");
		System.out.println(api.getResources());
	}

}
