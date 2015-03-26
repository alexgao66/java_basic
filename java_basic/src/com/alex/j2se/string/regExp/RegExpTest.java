package com.alex.j2se.string.regExp;

/**
 * 测试正则表达式
 * @author alex
 *
 */
public class RegExpTest {

	public static void main(String[] args) {
		rangeInRegExp();
	}
	
	/**
	 * 测试正则表达式中的字符类
	 */
	public static void characterInRegExp() {
		// 数字
		System.out.println("123".matches("\\d+"));
		// 字符
		System.out.println("next".matches("\\w{4}"));
		// 空格
		System.out.println(" ".matches("\\s"));
		// 任意字符
		System.out.println("abcd  13?".matches(".*"));
		// 字符集合
		System.out.println("abc".matches("[efabc]{3}"));
		// 反向字符集合
		System.out.println("abc".matches("[^def]{3}"));
		// 嵌套字符集合
		System.out.println("abc".matches("[a[bc][cd]]{3}"));
		// 字符区间
		System.out.println("abc".matches("[a-z]{3}"));
		// 数字区间
		System.out.println("123".matches("[1-9]{3}"));
	}
	
	/**
	 * 测试正则表达式中匹配字符的量词
	 * 一般正则表达式的匹配模式分为贪婪模式和懒惰模式，java中还有一种特有的“占用模式”
	 */
	public static void numInRegExp() {
		// 固定数量
		System.out.println("abc123".matches("\\w{3}\\d{3}"));
		// 零个或一个
		System.out.println("a".matches("\\w?"));
		// 零个或多个
		System.out.println("ab".matches("\\w*"));
		// 一个或多个
		System.out.println("abc".matches("\\w+"));
		// 至少若干个
		System.out.println("abc".matches("\\w{2,}"));
		// 数量区间
		System.out.println("abc".matches("\\w{2,5}"));
	}
	
	/**
	 * 测试正则表达式中的逻辑操作
	 */
	public static void logicInRegExp() {
		// 字符串先后顺序
		System.out.println("abc".matches("[a][b-d][c]"));
		// 字符串的或者关系
		System.out.println("abc".matches("a|b|c|d{3}"));
		// 匹配组
		System.out.println("abc-123-abc".matches("[abc]{3}-(\\d+)-[abc]{3}"));
	}
	
	/**
	 * 测试正则表达式的边界匹配
	 */
	public static void rangeInRegExp() {
		// 一行的起始
		System.out.println("abc".matches("^a[b-d]{2}"));
		// 一行的结束
		System.out.println("abc".matches("[a-d]{2}c$"));
	}
}
