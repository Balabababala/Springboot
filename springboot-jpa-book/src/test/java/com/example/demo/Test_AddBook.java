package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

import net.bytebuddy.description.NamedElement.WithOptionalName;

@SpringBootTest
public class Test_AddBook {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	@Test
	public void addAuthor() {
		//找作者
		Optional<Author> optAuthor = authorRepository.findById(1L);
		if(optAuthor.isEmpty()) {
			System.out.print("查無作者");
		}
		Author author = optAuthor.get();
		
		Book book1 =new Book();
		book1.setName("王八");
		book1.setAuthor(author);
		
		Book book2 =new Book();
		book2.setName("王八半");
		book2.setAuthor(author);
		
		bookRepository.save(book1);
		bookRepository.save(book2);
		
		
	}
	
}
