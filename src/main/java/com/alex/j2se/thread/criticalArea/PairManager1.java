package com.alex.j2se.thread.criticalArea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;



class Pair {
	private int x,y;
	
	public Pair(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public Pair() {
		this(0,0);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void incrementX() {
		++x;
	}
	
	public void incrementY() {
		++y;
	}
	
	public String toString() {
		return "x: " + x + " y: " + y;
	}
	public class PairValuesNotEqualException extends RuntimeException {

		private static final long serialVersionUID = -6792763387884269510L;
		
		public PairValuesNotEqualException() {
			super("Pair values not equal: " + Pair.this);
		}
	}
	
	public void checkState() {
		if(x != y) {
			throw new PairValuesNotEqualException();
		}
	}
}


abstract class PairManager{
	AtomicInteger checkCount = new AtomicInteger(0);
	
	protected Pair p = new Pair();
	
	private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
	
	public abstract void incement();
	
	public synchronized Pair getPair() {
		return new Pair(p.getX(), p.getY());
	}
	
	protected void store(Pair p) {
		storage.add(p);
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}

public class PairManager1 extends PairManager{

	@Override
	public synchronized void incement() {
		p.incrementX();
		p.incrementY();
		store(p);
	}
}

class PairManager2 extends PairManager {

	@Override
	public void incement() {
		Pair temp;
		synchronized(this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}


class PairManipulator implements Runnable {
	
	private PairManager pm;
	
	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}

	public void run() {
		while(true) {
			pm.incement();
		}
	}
	
	public String toString() {
		return "Pair: " + pm.getPair() + " checkCounter =" + pm.checkCount.get();
	}

}

class PairChecker implements Runnable {

	private PairManager pm;
	
	public PairChecker(PairManager pm) {
		this.pm = pm;
	}
	
	public void run() {
		while(true) {
			pm.checkCount.incrementAndGet();
			pm.getPair().checkState();
		}
	}
	
}


