package com.alex.j2se.oop;

import java.util.List;

public interface Processable<T> {
	void process(List<T> list);
	
	void process();
}
