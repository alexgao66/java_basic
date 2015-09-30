package com.alex.j2se.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 在方法内部定义FilenameFilter对应内部类实现文件过滤。
 * 使用正则表达式，获取该项目lib文件夹下spring相关的jar
 * 使用Arrays.sort进行排序
 * @author alex
 *
 */
public class DirList {
	public static void main(String[] args) {
		File file = new File("D:\\MyWorkSpace\\alex_classic\\WebRoot\\WEB-INF\\lib");
		String[] list = file.list(new FilenameFilter(){
			public boolean accept(File file, String name) {
				return Pattern.compile("^org\\.springframework.+").matcher(name).matches();
			}
		});
		Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
		
		for(String name : list) {
			System.out.println(name);
		}
	}
}
