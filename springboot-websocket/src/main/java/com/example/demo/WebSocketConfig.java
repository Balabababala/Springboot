package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // STOMP
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	
	//定義訊息路由規則
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");// 發送 到以 /topic 開頭的目的地     , EX:/topic/messages
		registry.setApplicationDestinationPrefixes("/app"); //應用程式 前綴 ,	EX:/app/chat 會對應到 @MessageChat("/chat")
	}

	
	//
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat-websocket").withSockJS();//WebSocket 端點
	}

	
}
