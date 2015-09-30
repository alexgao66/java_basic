package com.alex.j2se.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.StringReader;


/**
 * 按byte输出字符串
 * @author alex
 *
 */
public class ReadFromMemory {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		printFormatedStr("Test");
	}
	
	/**
	 * 使用stringreader打印输出字符串中的字符
	 * @param str
	 * @throws IOException
	 */
	public static void printStr(String str) throws IOException {
		StringReader strReader = new StringReader(str);
		int c;
		while((c = strReader.read()) != -1) {
			System.out.print((char)c);
		}
	}
	
	/**
	 * 使用dataInputStream打印输出字符串中的字符
	 * @param str
	 * @throws IOException
	 */
	public static void printFormatedStr(String str) throws IOException {
		DataInputStream dataInStream = new DataInputStream(new ByteArrayInputStream(str.getBytes()));
		while(dataInStream.available() != 0) {
			System.out.print((char)dataInStream.readByte());
		}
	}
}
