package com.example.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 門禁+樓層管制
 * 1. 每個用戶有自己的樓層
 * 2. 當用戶進入樓層時 AOP 會檢查權限
 * 3. 未授權進入的用戶 ,AOP 會拒絕
 * */

public interface FloorService {
	String enterFloor(String username ,int floor );
	
	
}
