package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Author;
import com.example.demo.repository.AuthorRepository;




@SpringBootTest
public class Test_Read {
	@Autowired
	private AuthorRepository authorRepository;
	

	
	@Test
	public void read() {
		//查詢作者
		List<Author> authors = authorRepository.findAll();
		authors.forEach(author->{
			System.out.printf("序號:%d 姓名:%s%n",author.getId(),author.getName());
		});
		
		
//		//查詢作者+書 // @OneToMany(mappedBy = "author",fetch =FetchType.EAGER) FetchType 要改成 EAGER 但他每次都會載入
//		List<Author> authors2 = authorRepository.findAll();
//		authors2.forEach(author->{
//			System.out.printf("序號:%d 姓名:%s 著作數量:%d%n",author.getId(),author.getName(),author.getBooks().size());
//		});
		
		//查詢作者+書 2
		List<Author> authors2 = authorRepository.findAllWithBooks();
		authors2.forEach(author->{
			System.out.printf("序號:%d 姓名:%s 著作數量:%d%n",author.getId(),author.getName(),author.getBooks().size());
		});
		
	}
}
