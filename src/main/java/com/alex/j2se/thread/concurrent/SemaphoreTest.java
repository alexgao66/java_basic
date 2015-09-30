package com.alex.j2se.thread.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 测试Semaphore
 * 通过信号量控制限定访问对象池中对象的数量
 * @author alex
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) throws InterruptedException {
		final Pool pool = new Pool("a","b","c","e","f","g");
		new Thread(new Runnable(){
			public void run() {
				String str;
				for(int i = 0; i < 5; ++i) {
					try {
						str = pool.getResource();
						System.out.println("A get :" + str);
						TimeUnit.SECONDS.sleep(5);
						pool.returnResource(str);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable(){
			public void run() {
				String str;
				for(int i = 0; i < 5; ++i) {
					try {
						str = pool.getResource();
						System.out.println("B get :" + str);
						TimeUnit.SECONDS.sleep(1);
						pool.returnResource(str);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}

}

class Pool {
	
	private static final int AVALIABLE_SIZE = 3;
	
	private List<String> resource;
	
	private Semaphore sempaphore;
	
	private List<Boolean> avaliable;
	
	public Pool(String... resource) {
		this.resource = new ArrayList<String>();
		Collections.addAll(this.resource, resource);
		avaliable = new ArrayList<Boolean>();
		for(int i = 0,length = resource.length; i < length; ++i) {
			avaliable.add(true);
		}
		sempaphore = new Semaphore(AVALIABLE_SIZE);
	}
	
	public String getResource() throws InterruptedException {
		sempaphore.acquire();
		return getNextAvaliableResource();
	}
	
	public void returnResource(String obj) {
		if(markAvaliable(obj)) {
			sempaphore.release();
		}
	}
	
	private synchronized String getNextAvaliableResource() {
		String item = null;
		for(int i = 0,length = resource.size(); i < length; ++i) {
			if(avaliable.get(i)) {
				item = resource.get(i);
				avaliable.set(i, false);
				break;
			}
		}
		return item;
	}
	
	private synchronized boolean markAvaliable(String obj) {
		int index = resource.indexOf(obj);
		if(index == -1) {
			return false;
		}else {
			avaliable.set(index, true);
			System.out.println("return :" + obj);
			return true;
		}
	}
}
