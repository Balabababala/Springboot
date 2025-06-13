package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.aop.CartService;

@SpringBootTest
public class CartTest {
	
	@Autowired
	private CartService cartService;
	
	@Test
	public void test() {
		String productId="A01";
		Integer quantity=100;
		Double price = -15.5;
		try {
			cartService.addToCart(productId, quantity, price);
		} catch (Exception e) {
		    System.err.printf(e.getMessage());
		}
		
		
	}
}
