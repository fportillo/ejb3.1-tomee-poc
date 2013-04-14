package br.com.almana;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "queue")
public class MessageReceiver implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("Message delivered");
		System.out.println(message.toString());
	}

}
