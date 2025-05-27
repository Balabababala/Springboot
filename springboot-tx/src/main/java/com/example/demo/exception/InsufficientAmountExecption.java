package com.example.demo.exception;

//餘額/庫存不足
public class InsufficientAmountExecption extends Exception{
	public InsufficientAmountExecption(String message) {
		super(message);
	}
}
