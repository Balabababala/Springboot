package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUsername(String username) ;
	public User save(User user);
}
