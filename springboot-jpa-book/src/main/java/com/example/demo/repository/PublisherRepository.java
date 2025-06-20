package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	
	@Modifying
	@Query(value= "delete from publisher_book where publisher_id = :publisherId and book_id = :bookId", nativeQuery=true)
	void deleteBookFromPublisher(@Param("publisherId") Long long1, @Param("bookId") Long long2);
	
	@Modifying
	@Query(value= "select from publisher_book join book where publisher_id = :publisherId and author_id = :authorId", nativeQuery=true)
	List<Publisher> hi(@Param("publisherId") Long long1 , @Param("authorObject") Long long3);//
	
}