package com.alex.j2se.string.regExp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试正则表达式中pattern和matcher类的使用
 * @author alex
 *
 */
public class PatternAndMatchTest {

	public static void main(String[] args) {
		reset();
	}
	
	/**
	 * 使用match方法进行全部匹配
	 */
	public static void match() {
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s(\\d{2}):\\d{2}:\\d{2}");
		Matcher matcher = pattern.matcher("2014-09-22 22:15:15");
		System.out.println(matcher.matches());
	}
	
	/**
	 * 使用find方法查找部分匹配，配合group可以获取捕获的字符串
	 */
	public static void find() {
		Pattern pattern = Pattern.compile("\\d{2}");
		Matcher matcher = pattern.matcher("2014-09-22 22:15:15");
		System.out.println(matcher.find());
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
	}
	
	/**
	 * 使用group方法获取捕获组的值
	 * 组号0表示整个字符串，不算入groupCount中
	 */
	public static void group() {
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s(\\d{2}):(\\d{2}):(\\d{2})");
		Matcher matcher = pattern.matcher("2014-09-22 21:15:15");
		matcher.matches();
		System.out.println(matcher.groupCount());
		System.out.println(matcher.group(0));
		System.out.println(matcher.group(1));
		// 匹配起始索引
		System.out.println(matcher.start());
		// 匹配截止索引
		System.out.println(matcher.end());
	}
	
	/**
	 * 测试reset方法
	 */
	public static void reset() {
		Matcher matcher = Pattern.compile(
				"\\d{4}-\\d{2}-\\d{2}\\s(\\d{2}):(\\d{2}):(\\d{2})").matcher(
				"2014-09-22 21:15:15");
		matcher.matches();
		System.out.println(matcher.group(1));
		matcher.reset("2014-09-22 08:15:15");
		matcher.matches();
		System.out.println(matcher.group(1));
	}
}
