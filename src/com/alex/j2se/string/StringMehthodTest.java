package com.alex.j2se.string;

public class StringMehthodTest {

	public static void main(String[] args) {
//		commonMethod();
//		specialSplit();
		replaceAll();
	}
	
	public static void specialSplit() {
		// 使用正则表达关键字做分隔时
		System.out.println("a.b.c".split(".").length);
	}
	
	public static void replaceAll() {
		System.out.println("http:\\/\\/dlsw.baidu.com\\/sw-search-sp\\/soft\\/cc\\/13478\\/npp.6.6.9.Installer.1410249599.exe".replace("\\/", "/"));
		System.out.println("abcbe".replace('b', 'f'));
	}

	public static void commonMethod() {
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
