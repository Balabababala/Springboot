package com.example.demo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	@MessageMapping("/chat")	//監聽 /app/chat 如果有事 SendTo
	@SendTo("/topic/messages")	//訂閱一個主題  主題:/messages
	public ChatMessage send(ChatMessage message) {
		return message;
	}
}
