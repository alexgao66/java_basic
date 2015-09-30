package com.alex.j2se.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 测试文件的输出
 * @author alex
 *
 */
public class FilePrinter {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		printFile(new File("D:\\MyWorkSpace\\alex_classic\\src\\com\\alex\\j2se\\io\\DirList.java"));
		printFromConsole3();
	}
	
	/**
	 * 将文件内容按行存入到一个LinkedList中，并倒序输出
	 * @param file
	 * @throws IOException
	 */
	public static void printFile1(File file) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		LinkedList<String> fileStrs = new LinkedList<String>();
		String str;
		while((str = bReader.readLine()) != null) {
			fileStrs.add(str);
		}
		for(int i = fileStrs.size()-1; i >= 0; --i) {
			System.out.println(fileStrs.get(i));
		}
	}
	
	/**
	 * 从控制台获取文件路径并输出，当输入exit时退出
	 * @throws IOException
	 */
	public static void printFromConsole1() throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while((str = bReader.readLine()) != null && !"exit".equals(str)) {
			if(str.startsWith("-file ")) {
				String fileName = str.substring(6, str.length());
				printFile1(new File(fileName));
			}
		}
		bReader.close();
	}
	
	/**
	 * 从控制台获取文件路径，并将文件内容按顺序大写输出
	 * @throws IOException
	 */
	public static void printFromConsole2() throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while((str = bReader.readLine()) != null && !"exit".equals(str)) {
			if(str.startsWith("-file ")) {
				String fileName = str.substring(6, str.length());
				sentContentToSysOut(new File(fileName));
			}
		}
		bReader.close();
	}
	
	/**
	 * 输出文件中包含指定关键字的行
	 * @param file
	 * @param key
	 * @throws IOException
	 */
	public static void printFile(File file,String key) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		String str;
		while((str = bReader.readLine()) != null) {
			if(str.indexOf(key) != -1) {
				System.out.println(str);
			}
		}
	}
	
	/**
	 * 从控制台获取文件夹路径及查询关键字，并输出指定行
	 * @throws IOException
	 */
	public static void printFromConsole3() throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while((str = bReader.readLine()) != null && !"exit".equals(str)) {
			if(str.startsWith("-file ") && str.indexOf("-key ") != -1) {
				String fileName = str.substring(6, str.indexOf("-key "));
				printFile(new File(fileName),str.substring(str.indexOf("-key ")+5, str.length()));
			}
		}
		bReader.close();
	}
	
	/**
	 * 将文件中的内容按大写格式输出。
	 * @param file
	 * @throws IOException
	 */
	public static void sentContentToSysOut(File file) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		List<String> fileStrs = new LinkedList<String>();
		String str;
		while((str = bReader.readLine()) != null) {
			fileStrs.add(str);
		}
		for(int length = fileStrs.size(),i = 0; i < length; ++i) {
			String item = fileStrs.get(i);
			fileStrs.set(i, item.toUpperCase());
			System.out.println(fileStrs.get(i));
		}
		
	}
	
	/**
	 * 正常的输出一个文件中的内容
	 * @param file
	 * @throws IOException
	 */
	public static void basicPrinter(File file) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		String str;
		while((str = bReader.readLine()) != null) {
			System.out.println(str);
		}
	}
	
}
