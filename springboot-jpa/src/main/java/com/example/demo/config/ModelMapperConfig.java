package com.example.demo.config;

import java.beans.BeanProperty;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	// Springboot 會自動建立此物件並管理
	// 其他程式可以透過 @Autowired 來取得該實體物件
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	} 
}
