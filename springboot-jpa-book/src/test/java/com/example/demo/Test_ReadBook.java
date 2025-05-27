package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Publisher;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;

@SpringBootTest
public class Test_ReadBook {

	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void read() {
//		//查所有書籍
//				List<Book> books =bookRepository.findAll();
//				books.forEach(book->{
//					System.out.printf("序號:%d 書名:%s%n",book.getId(),book.getName());
//				});
//		
//		
//		
//		//查所有書籍+作者
//		List<Book> books2 =bookRepository.findAll();
//		books2.forEach(book->{
//			System.out.printf("序號:%d 書名:%s 作者:%s%n",book.getId(),book.getName(),book.getAuthor().getName());
//		});
		
		//查詢同時由 ”全華圖書”與”博碩文化” 出版且作者是 ”王大明” 的書籍名稱

		
		
		
		List<Book> books3 =bookRepository.findAll();
		List<Publisher> publisher1 =publisherRepository.hi(1L, 1L);
		List<Publisher> publisher2 =publisherRepository.hi(2L, 1L);
		books3.forEach(book->{
			
		});
	}
}
