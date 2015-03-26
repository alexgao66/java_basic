package com.alex.j2se.servlet;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("Session create:\t" + event.getSession().getId());
		event.getSession().setAttribute("before", "b");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println(event.getSession().getAttribute("before"));
		System.out.println("Session destroy:\t" + event.getSession().getId());
	}

}
