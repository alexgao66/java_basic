package com.alex.j2se.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 遍历文件夹获取文件信息
 * @author alex
 *
 */
public class FileWalker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("E:\\life\\plan\\2012");
//		File file = new File("D:\\MyWorkSpace\\alex_classic\\WebRoot\\document");
//		printAll(file,"");
		File[] files = queryByContent(file, "javascript");
		for(File f : files) {
			System.out.println(f.getAbsolutePath());
		}
//		System.out.println(countLength(file));
	}
	
	/**
	 * 以“--”分割，输出文件层级结构
	 * @param file
	 * @param depthStr
	 */
	public static void printAll(File file,String depthStr) {
		System.out.print(depthStr);
		System.out.println(file.getName());
		if(file.isDirectory()) {
			for(File subFile : file.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return !name.equals(".svn");
				}
			})) {
				printAll(subFile,depthStr.concat("--"));
			}
		}
	}
	
	/**
	 * 遍历文件夹，查询包含指定关键字的文件
	 * @param file
	 * @param key
	 * @return
	 */
	public static File[] queryByContent(File file,final String key) {
		return file.listFiles(new FilenameFilter() {
			public boolean accept(File f, String name) {
				File subFile = new File(f.getAbsolutePath().concat("/").concat(name));
				boolean exist = false;
				if(subFile.isFile()) {
					BufferedReader br;
					try {
						br = new BufferedReader(
								new InputStreamReader(new FileInputStream(subFile),"GBK"));
						String line;
						while(!exist && (line = br.readLine()) != null) {
							exist = line.contains(key);
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return exist;
			}});
	}
	
	/**
	 * 计算文件夹下所有文件的大小总和
	 * @param file
	 * @return
	 */
	public static long countLength(File file) {
		long len = 0;
		if(file.isDirectory()) {
			for(File subFile : file.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return !name.equals(".svn");
				}
			})) {
				len += countLength(subFile);
			}
		}else {
			len = file.length();
		}
		return len;
	}
}
