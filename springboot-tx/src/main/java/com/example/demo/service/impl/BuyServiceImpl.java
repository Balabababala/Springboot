package com.example.demo.service.impl;

import java.lang.foreign.ValueLayout;
import java.math.BigDecimal;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.InsufficientAmountException;
import com.example.demo.service.BookService;
import com.example.demo.service.BuyService;




@Service
public class BuyServiceImpl implements BuyService{
	@Autowired BookService bookService;
	
	
	// RuntimeException 預設會回滾, 可以透過 dontRollbackOn 來改變
		// Exception 預設不會回滾, 可以透過 rollbackOn 來定義
	@Transactional(
			propagation = Propagation.REQUIRED, // 預設
			isolation = Isolation.DEFAULT, // 預設:使用資料庫預設
			rollbackFor = {InsufficientAmountException.class},
			noRollbackFor = {RuntimeException.class})
	@Override
	public void buyOneBook(String username,Long bookId) throws InsufficientAmountException{
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
