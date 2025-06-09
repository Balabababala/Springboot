package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//路由規則
@Configuration
@EnableWebSocketMessageBroker//啟用 STOMP WebSocket 支援
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	//註冊 Stomp 端點
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("chat-websocket").withSockJS();
	}

	//設 訊息代理 如何廣播 接收
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//用來可以處裡所有 /topic 開頭的訊息
		registry.enableSimpleBroker("/topic");
		
		//前端 傳送到 /app 開頭的訊息 會被路由到 @MessageMapping
		// EX: 前端對 /app/chat/{roomId} 發訊息 ,該訊息會被 廣播到 /topic/messages/{roomId}
		registry.setApplicationDestinationPrefixes("/app");
	}
	

}
