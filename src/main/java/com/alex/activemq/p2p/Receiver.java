package com.alex.activemq.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616");
		// 构造从工厂得到连接对象
		Connection connection;
		try {
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			Session session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			Destination destination = session.createQueue("FirstQueue");
			MessageConsumer consumer = session.createConsumer(destination);
			while (true) {
				//设置接收者接收消息的时间，为了便于测试，这里设定为100s
				TextMessage message = (TextMessage) consumer.receive(500000);
				if (null != message) {
					System.out.println("收到消息" + message.getText());
				} else {
					break;
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
      
	}

}
