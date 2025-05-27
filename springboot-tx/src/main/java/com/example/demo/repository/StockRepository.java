package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Stock;

import jakarta.transaction.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
	//取得書本庫存數量
	@Query(value ="SELECT book_amount FROM stock WHERE book_id = :bookId" ,nativeQuery = true)
	Long getBookAmount(@Param("bookId")Long bookId);
	
	//更新庫存數量
	@Modifying
	@Transactional
	@Query(value="UPDATE stock SET book_amount = book_amount - :amountToReduce WHERE book_id = :bookId",nativeQuery = true)
	void updateBookAmount(@Param("amountToReduce") Long amountToReduce, @Param("bookId") Long bookId);
	
	
}
