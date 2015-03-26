package com.alex.j2se.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;

/**
 * 测试输出信息到文件
 * @author alex
 *
 */
public class FileWriter {

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		writeAndRead(new File("d:/writeAndRead.txt"));
	}
	
	/**
	 * 将文件复制为以newFileName命名的同一文件夹下的文件
	 * @param file
	 * @param newFileName
	 * @throws IOException
	 */
	public static void basicFileWriter1(File file,String newFileName) throws IOException {
		BufferedReader in = 
				new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		File newFile = new File(file.getParent().concat("/").concat(newFileName));
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new java.io.FileWriter(newFile)));
		String str;
		int i = 1;
		while((str = in.readLine()) != null) {
			out.println(i++ + ":" + str);
		}
		out.close();
		FilePrinter.basicPrinter(newFile);
	}
	
	/**
	 * 使用lineNumberReader进行行计算
	 * @param file
	 * @param newFileName
	 * @throws IOException
	 */
	public static void basicFileWriter2(File file,String newFileName) throws IOException {
		LineNumberReader in = 
				new LineNumberReader(new InputStreamReader(new FileInputStream(file)));
		File newFile = new File(file.getParent().concat("/").concat(newFileName));
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new java.io.FileWriter(newFile)));
		String str;
		while((str = in.readLine()) != null) {
			out.println(in.getLineNumber() + ":" + str);
		}
		out.close();
		FilePrinter.basicPrinter(newFile);
	}
	
	
	/**
	 * 测试用户缓存进行读写与不使用缓存之间的速度差别
	 * @param file
	 * @throws IOException
	 */
	public static void basicFileWriter3(File file) throws IOException {
		long start1 = System.currentTimeMillis();
		BufferedReader in = 
				new BufferedReader(new InputStreamReader(new FileInputStream(file)),2048);
		File newFile = new File(file.getParent().concat("/buffered.txt"));
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new java.io.FileWriter(newFile)));
		String str;
		while((str = in.readLine()) != null) {
			out.println(str);
		}
		out.close();
		long end1 = System.currentTimeMillis();
		long bufferedTime = end1 - start1;
		
		long start2 = System.currentTimeMillis();
		FileReader in1 = new FileReader(file);
		java.io.FileWriter out1 = new java.io.FileWriter(new File(file.getParent().concat("/noBuffered.txt")));
		 int c;
		 /* 单个字符的读写
		while((c = in1.read()) != -1) {
			out1.write((char)c);
		}*/
		
		
		/*  合理调整缓存区比使用默认的bufferReader还快*/
		 
		char[] chBuff = new char[2048];
		
		while((c = in1.read(chBuff)) != -1) {
			out1.write(chBuff,0,c);
		}
		out1.close();
		long end2 = System.currentTimeMillis();
		long noBufferedTime = end2 - start2;
		
		System.out.println(bufferedTime);
		System.out.println(noBufferedTime);
	}
	
	/**
	 * 以系统默认格式
	 * @param file
	 * @throws Throwable
	 */
	public static void writeAndRead(File file) throws Throwable {
		DataOutputStream out = 
				new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		out.writeDouble(3.14159);
		out.writeUTF("That is pi");
		out.writeDouble(1.41413);
		out.writeUTF("square root of 2");
		out.close();
		
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		
	}
}
