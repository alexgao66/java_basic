package com.alex.j2se.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试Map，包括HashMap、Hashtable、LinkedHashMap、TreeMap、ConcurrentHashMap、ConcurrentSkipListMap
 * WeakHashMap
 * @author alex
 *
 */
public class MapTest {

	public static void main(String[] args) {
//		hashMapTest();
//		linkedHashMapTest();
//		mapTranservalWithEnrySetTest();
//		linkedHashMapAccessOrderTest();
//		treeMapTest();
//		mapTraservalWithIteratorTest();
//		mapTraservalWithKeySetTest();
//		hashTableTest();
		currentHashMapTest();
	}
	
	/**
	 * 测试<tt>HashMap</tt>p，使用散列值查询，无序
	 * 定义了<tt>EntryIterator</tt>进行遍历，EntryIterator继承至<tt>HashIterator</tt>
	 */
	private static void hashMapTest() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("key1", "val1");
		map.put("key3", "val3");
		map.put("key2", "val2");
		map.put("key1", "val1_1");
		map.put(null, null);
		System.out.println("HashMap: " + map);
	}
	
	/**
	 * 测试LinkedHashMap，与put的顺序一致
	 */
	private static void linkedHashMapTest() {
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("key1", "val1");
		map.put("key3", "val3");
		map.put("key2", "val2");
		map.put("key1", "val1_1");
		map.put(null, null);
		System.out.println("LinkedHashMap: " + map);
	}
	
	/**
	 * 测试AccessOrder为true时，LinkedHashMap对访问顺序的管理
	 */
	private static void linkedHashMapAccessOrderTest() {
		Map<String,String> map = new LinkedHashMap<String,String>(10,0.75f,true);
		map.put("key1", "val1");
		map.put("key3", "val3");
		map.put("key2", "val2");
		map.put("key1", "val1_1");
		map.put("key2", "val2_1");
		System.out.println("LinkedHashMap with true accessOrder: " + map);
		Map.Entry<String, String> entry = map.entrySet().iterator().next();
		System.out.println(entry);
		System.out.println(map.get(map.keySet().iterator().next()));
	}
	
	/**
	 * 测试TreeMap，保证key的自然排序
	 */
	private static void treeMapTest() {
		Map<String,String> map = new TreeMap<String,String>();
		map.put("key1", "val1");
		map.put("key3", "val3");
		map.put("key2", "val2");
		map.put("key1", "val1_1");
		System.out.println("TreeMap: " + map);
	}
	
	/**
	 * 测试hashtable
	 */
	private static void hashTableTest() {
		Hashtable<String, String> table = new Hashtable<String,String>();
		// key和value都不能为null，所以以下代码会报空指针错误。
		//table.put(null, null);
		//table.put(null, "nullVal");
		//table.put("nullKey", null);
		System.out.println(table);
		table.keySet();
		table.keys();
		table.entrySet();
	}
	
	/**
	 * 测试ConcurrentHashMap
	 */
	private static void currentHashMapTest() {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String,String>();
		// key和value都不能为null，所以以下代码会报空指针错误。
//		table.put(null, null);
//		table.put(null, "nullVal");
//		table.put("nullKey", null);
		System.out.println(map);
		map.keySet();
		map.keys();
		map.entrySet();
	}
	
	
	/**
	 * 使用增强for循环和Map的EntrySet遍历map
	 */
	private static void mapTranservalWithEnrySetTest() {
		Map<String,String> map = new HashMap<String,String>();
		for(int i = 0; i < 5; ++i) {
			map.put("key"+i, "value"+i);
		}
		for(Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
		}
	}
	
	/**
	 * 使用entrySet的iterator进行map遍历，比使用keyset快
	 */
	private static void mapTraservalWithIteratorTest() {
		Map<String,String> map = new HashMap<String,String>();
		for(int i = 0; i < 10000; ++i) {
			map.put("key"+i, "value"+i);
		}
		long start = System.currentTimeMillis();
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> entry= it.next();
			entry.getKey();
			entry.getValue();
		}
		System.out.println("map Traserval With Iterator using: " + (System.currentTimeMillis()-start));
	}
	
	
	/**
	 * 使用keySet进行map遍历
	 */
	private static void mapTraservalWithKeySetTest() {
		Map<String,String> map = new HashMap<String,String>();
		for(int i = 0; i < 10000; ++i) {
			map.put("key"+i, "value"+i);
		}
		long start = System.currentTimeMillis();
		Set<String> keyset = map.keySet();
		for(String key : keyset) {
			map.get(key);
		}
		System.out.println("map Traserval With key set using: " + (System.currentTimeMillis()-start));
	}
	
}
