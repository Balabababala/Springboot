package com.example.demo.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	//取書本價格
	@Query(value="SELECT price FROM book WHERE book_id = :bookId",nativeQuery = true)
	BigDecimal getBookPrice(@Param("bookId") Long bookid) ;
}
