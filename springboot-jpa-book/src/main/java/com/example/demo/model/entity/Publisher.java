package com.example.demo.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Publisher {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50,nullable = false)
	private String name;
	
	@ManyToMany
	@JoinTable(
			name="publisher_book",
			joinColumns = @JoinColumn(name="publisher_id"),
			inverseJoinColumns = @JoinColumn(name="book_id")
			)
	
	private List<Book> books = new ArrayList<>(); // ✅ 初始化
	
	// 自建一個新增書籍的方法
		public void addBook(Book book) {
			if(books == null) {
				books = new CopyOnWriteArrayList<>();
			}
			books.add(book);
		}
		
		public void removeBook(Book book) {
			if(books == null) {
				return;
			}
			books.remove(book);
			book.getPublishers().remove(this); // 同步移除對方集合
		}
	
}
