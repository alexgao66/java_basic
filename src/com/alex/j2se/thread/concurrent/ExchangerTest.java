package com.alex.j2se.thread.concurrent;

import java.util.concurrent.Exchanger;

/**
 * 测试concurrent包下的exchanger对象
 * exchanger可以在线程之间进行信息的交换，常用于管道的设计、缓存的交换、占用资源较大对象的创建和使用
 * @author alex
 *
 */
public class ExchangerTest {
	  public static void main(String args[]) {
		    Exchanger<String> exgr = new Exchanger<String>();

		    new UseString(exgr);
		    new MakeString(exgr);
		  }
		}
		
		class MakeString implements Runnable {
		  Exchanger<String> ex;

		  String str;

		  MakeString(Exchanger<String> c) {
		    ex = c;
		    str = new String();

		    new Thread(this).start();
		  }

		  public void run() {
		    char ch = 'A';
		    for (int i = 0; i < 3; i++) {
		      for (int j = 0; j < 5; j++)
		        str += (char) ch++;

		      try {
		        str = ex.exchange(str);
		      } catch (InterruptedException exc) {
		        System.out.println(exc);
		      }
		    }
		  }
		}

		class UseString implements Runnable {
		  Exchanger<String> ex;

		  String str;

		  UseString(Exchanger<String> c) {
		    ex = c;
		    new Thread(this).start();
		  }

		  public void run() {

		    for (int i = 0; i < 3; i++) {
		      try {
		        str = ex.exchange(new String());
		        System.out.println("Got: " + str);
		      } catch (InterruptedException exc) {
		        System.out.println(exc);
		      }
		    }
		  }
		}

