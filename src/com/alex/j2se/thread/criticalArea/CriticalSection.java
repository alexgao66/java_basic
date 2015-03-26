package com.alex.j2se.thread.criticalArea;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CriticalSection {
	static void testApproaches(PairManager pm1,PairManager pm2) {
		PairManipulator pMani1 = new PairManipulator(pm1),
				pMani2 =  new PairManipulator(pm2);
		PairChecker pch1 = new PairChecker(pm1),
				pch2 = new PairChecker(pm2);
		
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(pMani1);
		service.execute(pMani2);
		service.execute(pch1);
		service.execute(pch2);
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Sleep interruped");
		}
		System.out.println("pMani1: " + pMani1 + "\npMani2: " + pMani2);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		PairManager pm1 = new PairManager1(),
				pm2 = new PairManager2();
		testApproaches(pm1, pm2);		
	}
}
