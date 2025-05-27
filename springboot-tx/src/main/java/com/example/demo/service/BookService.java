package com.example.demo.service;

import java.math.BigDecimal;

import com.example.demo.exception.InsufficientAmountExecption;

public interface BookService {
	// 書本價格
	BigDecimal getBookPrice(Long bookId);
	
	// 書本庫存
	Long getBookAmount(Long bookId);
	
	// 帳戶餘額
	BigDecimal getWalletBalance(String username);
	
	// 更新庫存(減少庫存量)
	void reduceBookAmount(Long bookId, Long amountToReduce) throws InsufficientAmountExecption;
	
	// 更新餘額(減少餘額)
	void reduceWalletBalance(String username, BigDecimal bookPrice) throws InsufficientAmountExecption;
}