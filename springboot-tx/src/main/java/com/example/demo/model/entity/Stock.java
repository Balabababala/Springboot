package com.example.demo.model.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//庫存
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;//book.id 對應
	
	private Long bookAmount;//庫存數量
}
