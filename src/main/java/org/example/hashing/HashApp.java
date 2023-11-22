package org.example.hashing;

public class HashApp {

	public static void main(String[] args) {

		System.out.println("test information leaking");
		System.out.println(SHA256Helper.hash("Hello world!"));
		System.out.println(SHA256Helper.hash("Hello world"));

		System.out.println("blockchain uses double hashing");
		System.out.println(SHA256Helper.hash(SHA256Helper.hash("Hello world!")));

		System.out.println("test probability to find arbitrary hash with leading 0");
		System.out.println(SHA256Helper.hash("Hello world1"));
		System.out.println(SHA256Helper.hash("Hello world2"));
		System.out.println(SHA256Helper.hash("Hello world3"));
		System.out.println(SHA256Helper.hash("Hello world4"));
		System.out.println(SHA256Helper.hash("Hello world5"));
		System.out.println(SHA256Helper.hash("Hello world6"));
		System.out.println(SHA256Helper.hash("Hello world7"));
		System.out.println(SHA256Helper.hash("Hello world8"));
		System.out.println(SHA256Helper.hash("Hello world9"));
		System.out.println(SHA256Helper.hash("Hello world10"));
		System.out.println(SHA256Helper.hash("Hello world11"));
		System.out.println(SHA256Helper.hash("Hello world12"));
		System.out.println(SHA256Helper.hash("Hello world13"));
		System.out.println(SHA256Helper.hash("Hello world14"));
		System.out.println(SHA256Helper.hash("Hello world15"));
		System.out.println(SHA256Helper.hash("Hello world16"));
		
	}
}
