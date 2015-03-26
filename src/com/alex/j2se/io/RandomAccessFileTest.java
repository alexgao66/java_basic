package com.alex.j2se.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 测试RandomAccessFile
 * @author alex
 *
 */
public class RandomAccessFileTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		writeFile("d:/raf.dat");
		printFile("d:/raf.dat");
	}
	
	/**
	 * 写入
	 * @param path
	 * @throws Exception
	 */
	public static void writeFile(String path) throws Exception {
		RandomAccessFile file = new RandomAccessFile(new File(path), "rw");
		for(int i = 0; i < 7; ++i) {
			file.writeDouble(i * 1.414);
		}
		file.writeUTF("This is the end of the file");
		file.close();
	}
	
	/**
	 * 读取
	 * @param path
	 * @throws IOException
	 */
	public static void printFile(String path) throws IOException {
		RandomAccessFile file = new RandomAccessFile(new File(path), "r");
		for(int i = 0; i < 7; ++i) {
			System.out.println(file.readDouble());
		}
		System.out.println(file.readUTF());
		file.close();
	}
}
