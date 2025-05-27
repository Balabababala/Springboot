package com.example.demo.service.impl;

import java.math.BigDecimal;

import javax.naming.InsufficientResourcesException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InsufficientAmountExecption;
import com.example.demo.service.BookService;
import com.example.demo.service.BuyService;

import jakarta.transaction.Transactional;

@Transactional(rollbackOn = {InsufficientResourcesException.class},
				dontRollbackOn= {RuntimeException.class})
@Service
public class BuyServiceImpl implements BuyService{
	@Autowired BookService bookService;
	
	
	public void buyOneBook(String username,Long bookId) throws InsufficientAmountExecption{
		System.out.printf("%s 要買書 %n", username);
		// 1. 查詢書本價格
		BigDecimal bookPrice = bookService.getBookPrice(bookId);
		System.out.printf("bookId: %d, bookPrcie: %s%n", bookId, bookPrice);
		// 2. 減去庫存(1本)
		bookService.reduceBookAmount(bookId, 1L) ; // 固定減去 1 本
		// 3. 修改餘額
		bookService.reduceWalletBalance(username, bookPrice);
		// 4. 結帳完成
		System.out.printf("%s 結帳完成%n", username);
		System.out.printf("%s", 10/0);
	};
}
