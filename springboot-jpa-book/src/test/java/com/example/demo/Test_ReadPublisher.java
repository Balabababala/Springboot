package com.example.demo;



import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Publisher;
import com.example.demo.repository.PublisherRepository;

@SpringBootTest
public class Test_ReadPublisher {
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Test
	public void read() {
		List<Publisher> publishers=publisherRepository.findAll();
		
		
	}
}
