package br.com.almana;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.NamingException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//FIXME: This should not be an ejb at all, but even binding this to the context
// connectionFactory is not initialized
@Stateless
public class MessageReceiverTest extends EJBContextAware {

	@Resource
	private Queue queue;
	
	@Resource
	private ConnectionFactory connectionFactory;
	
	@BeforeMethod
	public void setup() throws NamingException {
	}

	@Test
	public void sendMessageToQueue() throws JMSException, NamingException {
		bind();
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer messageProducer = session.createProducer(queue);
		messageProducer.send(session.createTextMessage("Hello!"));
		session.close();
		connection.close();
	}
}
