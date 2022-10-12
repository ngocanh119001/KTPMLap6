package com.example.demo.app;

import com.google.gson.Gson;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class Listener {
	// @JmsListener: Annotation chỉ định các cấu hình cho bộ lắng nghe tin nhắn của
	// JMS. Chủ yếu là để cấu hình địa chỉ nhận tin nhắn (destination). Trong trường
	// hợp này, destination là “inbound.queue“
	@JmsListener(destination = "inbound.queue")
	// @SendTo: Annotation chỉ định địa chỉ mà JMS cần gửi tin nhắn đến, ở phương
	// thức được cấu hình với SendTo, tin nhắn sẽ được gửi đi thông qua việc trả về
	// giá trị của phương thức đó.
	@SendTo("outbound.queue")
	public String receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Tin nhắn nhận được: " + jsonMessage);
		String response = null;
		if (jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) jsonMessage;
			messageData = textMessage.getText();
			Map map = new Gson().fromJson(messageData, Map.class);
			response = "Chào " + map.get("name");
		}
		return response;
	}
}
