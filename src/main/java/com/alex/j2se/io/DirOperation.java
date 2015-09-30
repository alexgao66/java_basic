package com.alex.j2se.io;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class DirOperation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		printFileInfo(new File("D:/MyWorkSpace/alex_classic\\src\\com\\alex\\j2se\\io\\DirOperatioin.java"));
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 1, 11);
		List<File> files = getModifiedFiles(new File("D:\\MyWorkSpace\\alex_classic"), new Date(cal.getTimeInMillis()));
		for(File f : files) {
			System.out.println(f.getAbsolutePath());
		}
	}
	
	/**
	 * 输出文件常用信息
	 * @param file
	 */
	public static void printFileInfo(File file) {
		System.out.println("Absolute path: " + file.getAbsolutePath()
				+ "\nCan read: " + file.canRead()
				+ "\nCan write: " + file.canWrite()
				+ "\ngetName: " + file.getName()
				+ "\ngetParent: " + file.getParent()
				+ "\ngetPath: " + file.getPath()
				+ "\nlength: " + file.length()
				+ "\nlastModified: " + new Timestamp(file.lastModified()));
		if(file.isFile()) {
			System.out.println("It's a file.");
		} else if(file.isDirectory()) {
			System.out.println("It's a directory.");
		}
	}
	
	/**
	 * 获取指定时间之后修改的java文件
	 * @param file
	 * @param date
	 * @return
	 */
	public static List<File> getModifiedFiles(File file,Date date) {
		ArrayList<File> files = new ArrayList<File>();
		if(file.isFile()) {
			if(Pattern.compile("^.*\\.(txt|java)$").matcher(file.getName()).matches() && file.lastModified() > date.getTime()) {
				files.add(file);
			}
		}else if(file.isDirectory() && !file.getName().equals(".svn")) {
			for(File f : file.listFiles()) {
				files.addAll(getModifiedFiles(f,date));
			}
		}
		return files;
	}
	
}
