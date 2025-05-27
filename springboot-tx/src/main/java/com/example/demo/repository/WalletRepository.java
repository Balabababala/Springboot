package com.example.demo.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Wallet;

import jakarta.transaction.Transactional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String>{

	//取得客戶餘額
	@Query(value = "SELECT balance FROM wallet WHERE username = :username",nativeQuery = true)
	BigDecimal getWalletBallance(@Param("username") String username);
	
	//取得客戶餘額
	@Modifying
	@Transactional
	@Query(value = "UPDATE wallet SET balance = balance - :bookPrice WHERE username=:username",nativeQuery = true)
	void updateWalletBalance(@Param("bookPrice") BigDecimal bookPrice,@Param("username") String username);
	
	
	
	
}
