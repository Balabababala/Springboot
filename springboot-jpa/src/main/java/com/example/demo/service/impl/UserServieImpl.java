package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.*;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Hash;

@Service
public class UserServieImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDto getUser(String userName) {
		User user =userRepository.findByUsername(userName);
		if(user==null) {
			return null;
		}
		return userMapper.toDto(user);
	}

	@Override
	public void addUser(String username, String password, String email, Boolean active, String role) {
		String salt = Hash.getSalt();
		String passwordHash = Hash.getHash(password, salt);
		User user = new User(null, username, passwordHash, salt, email, active, role);
		userRepository.save(user);
	}

	
	
}
