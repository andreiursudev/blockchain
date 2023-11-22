package org.example.hashing;

public class HashApp {

	public static void main(String[] args) {

		//test information leaking
		System.out.println(SHA256Helper.hash("Hello world!"));
		System.out.println(SHA256Helper.hash("Hello world"));

		//blockchain uses double hashing
		System.out.println(SHA256Helper.hash(SHA256Helper.hash("Hello world!")));
		
	}
}
