package com.example.demo.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.entity.ChatMessage;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

// 處理 WebSocket 聊天訊息:支援多聊天室, 根據 roomId 可以廣播到不同聊天室 
@Controller
public class TimerPublisher {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	/**
	 * 處理來自 "/app/chat/{roomId}" 的訊息, 並將訊息廣播到 "/topic/messages/{roomId}"
	 * @param roomId 聊天室 Id
	 * @param chatMessage 聊天訊息
	 * */
	@Scheduled(fixedRate=1000)
	public void sendToRoom3() {
		ChatMessage chatMessage =new ChatMessage();
		chatMessage.setFrom("你媽");
		chatMessage.setContent("快來吃飯 現在時間"+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		
		messagingTemplate.convertAndSend("/topic/messages/room3" , chatMessage);
	}
	
}