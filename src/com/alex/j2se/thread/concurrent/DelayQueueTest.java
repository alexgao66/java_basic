package com.alex.j2se.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 测试DelayQueue，用于取出最先达到超时时间的延迟对象。
 * @author alex
 *
 */
public class DelayQueueTest {

	public static void main(String[] args) {
		final List<DelayQueueTest.DelayedElement> delayElsList 
			= new ArrayList<DelayQueueTest.DelayedElement>();
		delayElsList.add(new DelayQueueTest.DelayedElement(1200));
		delayElsList.add(new DelayQueueTest.DelayedElement(100));
		delayElsList.add(new DelayQueueTest.DelayedElement(300));
		delayElsList.add(new DelayQueueTest.DelayedElement(200));
		final DelayQueue<DelayQueueTest.DelayedElement> dq = new DelayQueue<DelayQueueTest.DelayedElement>();
		
		new Thread(new Runnable() {
			public void run() {
				for(DelayQueueTest.DelayedElement de : delayElsList) {
					dq.put(de);
				}
			}
		}).start();
		/*try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		new Thread(new Runnable() {
			public void run() {
				try {
					for(int i=0,length = delayElsList.size(); i < length; ++i) {
						DelayQueueTest.DelayedElement de = dq.take();
						System.out.println(de);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	static class DelayedElement implements Delayed{
		
		/**
		 * 超时时间，单位：纳秒
		 */
		private long expiredTime;
		
		private long removeTime;
		
		public DelayedElement(long time) {
			this.expiredTime = time;
			removeTime = TimeUnit.NANOSECONDS.convert(time + System.nanoTime(), TimeUnit.NANOSECONDS);
		}

		public int compareTo(Delayed o) {
			DelayedElement el = (DelayedElement)o;
			if(expiredTime > el.expiredTime) {
				return 1;
			}else if(expiredTime < el.expiredTime){
				return -1;
			}
			return 0;
		}
		
		public long getDelay(TimeUnit unit) {
			return unit.convert(removeTime-System.nanoTime(), TimeUnit.NANOSECONDS);
		}
		
		public String toString() {
			return String.valueOf(this.expiredTime);
		}
		
	}
}
