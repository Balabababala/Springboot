package com.example.demo.config;

import java.beans.BeanProperty;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}
	
}
