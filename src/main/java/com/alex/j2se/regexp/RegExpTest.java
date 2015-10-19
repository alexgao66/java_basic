package com.alex.j2se.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpTest {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("^/.*$");
		Matcher m = p.matcher("/……￥##……");
		System.out.println(m.matches());
	}

}
