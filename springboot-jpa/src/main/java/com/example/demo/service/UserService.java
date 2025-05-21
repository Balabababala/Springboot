package com.example.demo.service;



import com.example.demo.model.dto.UserDto;


public interface UserService {
	public UserDto getUser(String username);
	public void addUser(String username ,String passWord, String email,Boolean active,String role);
	
	
}
