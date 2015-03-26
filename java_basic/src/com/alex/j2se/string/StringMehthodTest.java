package com.alex.j2se.string;

public class StringMehthodTest {

	public static void main(String[] args) {
		// split
		System.out.println("a;b;c".split(";").length);
		// charAt
		System.out.println("a;b;c".charAt(1));
		// getBytes
		System.out.println("a;b;c".getBytes()[1]);
		// getCharArray
		System.out.println("a;b;c".toCharArray()[1]);
		// contains
		System.out.println("a;b;c".contains("a;"));
		// contenEquals
		System.out.println("a;b;c".contentEquals("a;b;c"));
		// startWith
		System.out.println("a;b;c".startsWith("a;"));
		System.out.println("a;b;c".startsWith(";b", 1));
		// indexOf
		System.out.println("a;b;c".indexOf(";"));
		// lastIndexOf
		System.out.println("a;b;c".lastIndexOf(";"));
		// subString
		System.out.println("a;b;c".substring(1));
		// concat
		System.out.println("a;b;c".concat(";d"));
		// replace
		System.out.println("a;b;c".replace(';', ':'));
		System.out.println("a;b;c".replace(";c", ":c"));
		// toUpperCase
		System.out.println("a;b;c".toUpperCase());
		// trim
		System.out.println("           a;b;   c    ".trim());
	}
}
