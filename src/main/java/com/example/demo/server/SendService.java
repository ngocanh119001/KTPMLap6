package com.example.demo.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendService {
	private JmsTemplate jmsTemplate;

	@Autowired
	public SendService(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendMess(String mess) {
		jmsTemplate.convertAndSend("Ngọc Ánh", mess);
	}

}
