package com.alex.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MQBasicSender {

	public static void main(String[] args) {
		ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, 
				ActiveMQConnection.DEFAULT_USER, "tcp://localhost:61616");
		Connection conn;
		Session session;
		try {
			conn = factory.createConnection();
			conn.start();
			session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
