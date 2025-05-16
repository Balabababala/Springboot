package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Book;

@Primary
@Repository("BookRepositoryJdbcImpl")
public class BookRepositoryJdbcImpl implements BookRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Book> findAllBooks(){
		String sql="select id ,name ,price ,amount ,pub from book"; //不要用*
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Book.class));
	}

	@Override
	public Optional<Book> getBookById(Integer id) {
		String sql="select id ,name ,price ,amount ,pub from book where id=?";  
//		List<Book> books =jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Book.class),id);
//		return books.isEmpty()?Optional.empty():Optional.of(books.get(0));
		try {
			Book book = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Book.class));
			return Optional.of(book);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
		
	}

	@Override
	public boolean addBook(Book book) {
//		if(book.getPub()==null) {
//			book.setPub(false);
//		}
		String sql="insert into book(name,price,amount,pub) values (?,?,?,?)";  
		int rows=jdbcTemplate.update(sql,book.getName(),book.getPrice(),book.getAmount(),book.getPub());
		return rows>0;
	}

	@Override
	public boolean updateBook(Integer id, Book book) {
//		if(book.getPub()==null) {
//			book.setPub(false);
//		}
		String sql="UPDATE book SET name=? ,price=? ,amount=?,pub=? where id=?";  
		int rows=jdbcTemplate.update(sql,book.getName(),book.getPrice(),book.getAmount(),book.getPub(),id);
		return rows>0;
	}

	@Override
	public boolean deleteBook(Integer id) {
		String sql="delete from book where id= ?";
		int rows=jdbcTemplate.update(sql,id);
		return rows>0;
//		return jdbcTemplate.update("delete from book where id= ?",id)>0;
	}
	
	
}
