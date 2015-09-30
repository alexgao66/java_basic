package com.alex.j2se.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 使用map统计指定文件中不同字符出现的次数
 * @author alex
 *
 */
public class CharDispCount {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Map<Character,Integer> map = countChar(new File("d:/dirList.java"));
		Iterator<Entry<Character,Integer>> it = map.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Character,Integer> entry = it.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	public static Map<Character,Integer> countChar(File file) throws IOException {
		FileReader in = new FileReader(file);
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		int i;
		while((i = in.read()) > 0) {
			char c = (char)i;
			Integer count = 1;
			if(map.get(c) != null) {
				count = Integer.valueOf(map.get(c)) + 1;
			}
			map.put(c, count);
		}
		in.close();
		return map;
	}
}	
