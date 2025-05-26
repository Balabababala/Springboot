package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Biography;

@Repository
public interface BiographyRepository extends JpaRepository<Biography, Integer> {
	@Query("select b from Biography b inner join b.author") //inner 左右欄位都有 left左邊欄位才有 
	//@Query("select b from Biography b left join b.author")
	List<Biography> findAllWithAuthor();
	
	
}