package com.alex.j2se.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试callable接口和Future对象
 * @author alex
 *
 */
public class CallableTest {

	static Integer counterVal = 10000;

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		List<Future<Integer>> vals = new ArrayList<Future<Integer>>();
		for (int i = 0; i < 10; ++i) {
			// 使用submit调用callable对象
			vals.add(es.submit(new counter()));
		}
		try {
			for (Future<Integer> future : vals) {
				// 使用Future的get方法获取执行结果
				System.out.println(future.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/**
 * 测试callable接口，获取人执行之后的结果
 * 
 * @author alex
 * 
 */
class counter implements Callable<Integer> {

	public Integer call() throws Exception {
		for(int i = 0; i < 10000; ++i) {
			++CallableTest.counterVal;
		}
		return CallableTest.counterVal;
	}
}