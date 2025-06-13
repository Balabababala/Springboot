package com.example.demo.aop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class AccessController {
	//模擬用戶授權樓層資料(in-memory)
	private static final Map<String ,Set<Integer>> userFloorMap =new HashMap<>() ;
	
	static {
		userFloorMap.put("alice", Set.of(1,2,3));
		userFloorMap.put("guest", Set.of(1));
		userFloorMap.put("security", Set.of(1,2,3,4,5));
	}
	
	public static boolean hasAccess(String username , int floor) {
		return userFloorMap.getOrDefault(username, Set.of()).contains(floor);
	}
}
