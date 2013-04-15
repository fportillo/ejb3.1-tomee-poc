package br.com.almana;

import static org.testng.AssertJUnit.assertTrue;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
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

	@Resource(name = "MessageReceiver")
	private Queue queue;
	
	@Resource
	private ConnectionFactory connectionFactory;
	
	@BeforeMethod
	public void setup() throws NamingException {
	}

	@Test
	public void sendMessageToQueue() throws JMSException, NamingException, InterruptedException {
		bind();
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(queue);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		producer.send(session.createTextMessage("Hello World!"));
		assertTrue(session.getAcknowledgeMode() == Session.AUTO_ACKNOWLEDGE);
		session.close();
		connection.close();
	}
}
