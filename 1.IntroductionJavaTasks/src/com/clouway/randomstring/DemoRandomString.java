package com.clouway.randomstring;

public class DemoRandomString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomString rand = new RandomString();
		String str = rand.generateRandomString(32, 1);
		
		System.out.println(str);

	}

}
