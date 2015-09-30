package com.alex.j2se.io;

import java.io.File;

/**
 * 递归输出文件夹下所有文件的名称，并体现出文件层级
 * @author alex
 *
 */
public class RecurrenceDirList {
	
	public static void main(String[] args) {
		printDir(new File("D:\\photo"),0);
	}
	
	/**
	 * @param file 文件对象
	 * @param depth 文件深度
	 */
	public static void printDir(File file,int depth) {
		for(int i = 0; i < depth; ++i) {
			System.out.print("\t");
		}
		System.out.println(file.getName());
		if(file.isDirectory()) {
			depth += 1;
			for(File subFile : file.listFiles()) {
				printDir(subFile,depth);
			}
		}
	}
	
}
