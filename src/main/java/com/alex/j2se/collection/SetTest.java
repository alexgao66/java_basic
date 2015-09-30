package com.alex.j2se.collection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 测试Set，包括HashSet、LinkedHashSet、TreeSet、ConcurrentSkipListSet、copyOnWriteArraySet
 * copyOnWriteArraySet类似TreeSet，但是是线程安全的，但存在数据不一致的情况。TreeSet是非线程安全的。
 * @author alex
 *
 */
public class SetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		hashSetTest();
		treeSetTest();
		treeSetWithComparableTest();
		linkedHashSetTest();
		treeSetWithComparatorTest();
		concurrentSkipListSetTest();
	}
	
	/**
	 * HashSet使用散列码进行数据查询
	 * HashSet内部使用HashMap实现的
	 */
	private static void hashSetTest() {
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("a");
		set.add("1");
		set.add("3");
		set.add("2");
		
		System.out.println("HashSet: "+set);
	}
	
	/**
	 * TreeSet按照自然顺序进行排序set元素
	 * TreeSet内部使用TreeMap实现的
	 */
	private static void treeSetTest() {
		TreeSet<String> set = new TreeSet<String>();
		set.add("a");
		set.add("b");
		set.add("a");
		set.add("1");
		set.add("3");
		set.add("2");
		System.out.println("TreeSet with default order: "+set);
		// 测试first方法，获取（排序之后的）第一个元素
		System.out.println("First element in treeSet with default order: "+set.first());
		// 测试ceiling方法，获取大于或等于指定元素的第一个元素
		System.out.println("Ceiling element of '2.5' in treeSet with default order: "+set.ceiling("2.5"));
		// 测试floor方法，获取小于或等于指定元素的第一个元素
		System.out.println("Floor element of '2.5' in treeSet with default order: "+set.floor("2.5"));
		// 获取反向的set视图
		System.out.println("Get descend view of treeSet with default order:" + set.descendingSet());
		// 测试headSet方法，获取小于指定元素的set视图
		System.out.println("headset view below '3' in treeSet with default order:" + set.headSet("3"));
		// 测试headSet，获取小于或等于指定元素的set视图
		System.out.println("headset view include '3' in treeSet with default order:" + set.headSet("3",true));
	}
	
	/**
	 * 使用TreeSet对Comparable对象的进行排序
	 */
	private static void treeSetWithComparableTest() {
		Set<Person> set = new TreeSet<Person>();
		set.add(new Person(12, "xiao"));
		set.add(new Person(28, "gao"));
		set.add(new Person(27, "han"));
		set.add(new Person(10, "ya"));
		System.out.println("TreeSet with Comparable class: "+set);
	}
	
	/**
	 * 使用有Comparator的TreeSet进行对象排序
	 */
	private static void treeSetWithComparatorTest() {
		Set<Animal> set = new TreeSet<Animal>(new Comparator<Animal>() {

				public int compare(Animal p1, Animal p2) {
					if(p1.equals(p2)) {
						return 0;
					}else if(p1.age > p2.age) {
						return 1;
					}else {
						return -1;
					}
				}
			});
			set.add(new Animal(12, "dog"));
			set.add(new Animal(28, "cat"));
			set.add(new Animal(27, "tiger"));
			set.add(new Animal(10, "pig"));
			System.out.println("TreeSet with comparator: "+set);
	}
	
	/**
	 * LinkedHashSet按照插入的顺序进行存储
	 * LinkedHashSet继承至HashSet，内部是使用LinkdeHashMap实现的
	 */
	private static void linkedHashSetTest() {
		Set<String> set = new LinkedHashSet<String>();
		set.add("a");
		set.add("b");
		set.add("a");
		set.add("1");
		set.add("3");
		set.add("2");
		System.out.println("LinkedHashSet: "+set);
	}
	
	/**
	 * 用于测试排序的对象，实现Comparable接口
	 * @author alex
	 *
	 */
	private static class Person implements Comparable<Person>{
		
		public int age;
		
		public String name;
		
		public Person(int age,String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public String toString() {
			return "{name: "+name+ ",age: "+age+"}";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + age;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Person other = (Person) obj;
			if (age != other.age)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		public int compareTo(Person p2) {
			if(this.equals(p2)) {
				return 0;
			}else if(this.age > p2.age) {
				return 1;
			}else {
				return -1;
			}
		}
		
	} 
	
	/**
	 * 用于测试排序的对象
	 * @author alex
	 *
	 */
	private static class Animal {
		
		public int age;
		
		public String name;
		
		public Animal(int age,String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public String toString() {
			return "{name: "+name+ ",age: "+age+"}";
		}
	}
	
	private static void concurrentSkipListSetTest() {
		ConcurrentSkipListSet<String> set = new ConcurrentSkipListSet<String>(); 
		set.add("a");
		set.add("b");
		set.add("a");
		set.add("1");
		set.add("3");
		set.add("2");
		System.out.println("ConcurrentSkipListSet: "+set);
	}
}	
