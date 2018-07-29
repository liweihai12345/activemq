package com.ccyt.jms.queue;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * Created by liweihai on 2018/7/16.
 */
public class AppProducer {
    private static final String url = "tcp://192.168.0.110:61616";
    private static final String queueName = "queue-test";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("test1" + i);
            producer.send(textMessage);
            System.out.println("发送消息1" + textMessage.getText());
        }
        connection.close();

    }

}
