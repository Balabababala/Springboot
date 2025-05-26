package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@SpringBootTest
public class Test_UpdateBook {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	
	//變更書籍作者 將書籍的作者欄位變更
	@Test
	public void updateBookAuthorName() {
		Optional<Author> optAuthor = authorRepository.findById(2L);
		if(optAuthor.isEmpty()) {
			System.out.printf("查無作者");
			return;
		}
		Optional<Book> optBook = bookRepository.findById(1L);
		if(optBook.isEmpty()) {
			System.out.printf("查無書籍");
			return;
		}

		Author author =optAuthor.get();
		Book book =optBook.get();
		//更新設定
		book.setAuthor(author);
		
		//commit
		bookRepository.save(book);

		
	}
}
