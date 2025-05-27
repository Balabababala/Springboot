package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.service.BuyService;




@Component
public class TXCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private BuyService buyService;
	
	// 買書
	private void buyBook(String username, Long bookId) {
		try {
		buyService.buyOneBook(username, bookId);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	public void run(String... args) throws Exception {
			buyBook("john", 1L);
	}

}