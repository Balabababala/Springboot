package com.example.demo.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InsufficientAmountExecption;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private WalletRepository walletRepository;
	
	// 書本價格
	@Override
	public BigDecimal getBookPrice(Long bookId) {
		return bookRepository.getBookPrice(bookId);
	}

	// 書本庫存
	@Override
	public Long getBookAmount(Long bookId) {
		return stockRepository.getBookAmount(bookId);
	}
	// 帳戶餘額
	@Override
	public BigDecimal getWalletBalance(String username) {
		return walletRepository.getWalletBallance(username);
	}
	// 更新庫存(減少庫存量)
	@Override
	public void reduceBookAmount(Long bookId, Long amountToReduce) throws InsufficientAmountExecption {
		//1. 檢查庫存
		Long bookAmount =getBookAmount(bookId);
		if (bookAmount<amountToReduce) {
			throw new InsufficientAmountExecption(String.format("bookId: %d 庫存不足 (%d<%d)%n", bookId,bookAmount,amountToReduce));
		}
		//2.執行
		stockRepository.updateBookAmount(amountToReduce, bookId);
		
	}
	// 更新餘額(減少餘額)
	@Override
	public void reduceWalletBalance(String username, BigDecimal bookPrice) throws InsufficientAmountExecption {
		// 1. 檢查餘額
		BigDecimal walletBalance = getWalletBalance(username);
		if(walletBalance.compareTo(bookPrice) <0 ) {
			throw new InsufficientAmountExecption(String.format("username: %s 餘額不足 (%d < %d)%n", username, walletBalance, bookPrice));
		}
		// 2. 更新餘額
		walletRepository.updateWalletBalance(bookPrice, username);
	}

}
