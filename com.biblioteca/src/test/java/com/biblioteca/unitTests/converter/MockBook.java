package com.biblioteca.unitTests.converter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.data.model.Book;
import com.biblioteca.data.object.BookDO;

public class MockBook {
	
	public MockBook() {
		
	}

	
	public Book mockModel(Integer integer) {
		Book book = new Book();
		book.setAutor("Very Smart Autor " + integer);
		book.setAvaliable(integer % 2 == 0);
		
		Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		book.setBorrowingDate(date);
		// return date is 15 days after borrowing
		Date date2 = new Date(millis + 1296000000);
		book.setReturnDate(date2);
		book.setId(integer + 10L);
		book.setName("Super Duper Cool Book " + integer);
		book.setReserved(integer % 2 == 1);
		book.setVolume(integer);
		
		return book;
	}
	
	public BookDO mockObject(Integer integer) {
		BookDO book = new BookDO();
		book.setAutor("Very Smart Autor " + integer);
		book.setAvaliable(integer % 2 == 0);
		
		Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		book.setBorrowingDate(date);
		// return date is 15 days after borrowing
		Date date2 = new Date(millis + 1296000000);
		book.setReturnDate(date2);
		book.setKey(integer + 10L);
		book.setName("Super Duper Cool Book " + integer);
		book.setReserved(integer % 2 == 1);
		book.setVolume(integer);
		
		return book;
	}
	
	public Book mockModel() {
		return mockModel(0);
	}
	
	public BookDO mockObject() {
		return mockObject(0);
	}
	
	public List<Book> mockModelList(Integer integer) {
		List<Book> books = new ArrayList<>();
		
		for(int i = 0; i < integer; i++) {
			books.add(mockModel(i));
		}
		return books;
	}
	
	public List<BookDO> mockObjectList(Integer integer) {
		List<BookDO> books = new ArrayList<>();
		
		for(int i = 0; i < integer; i++) {
			books.add(mockObject(i));
		}
		return books;
	}
	
	public List<Book> mockModelList() {
		List<Book> books = new ArrayList<>();
		
		for(int i = 0; i < 15; i++) {
			books.add(mockModel(i));
		}
		return books;
	}
	
	public List<BookDO> mockObjectList() {
		List<BookDO> books = new ArrayList<>();
		
		for(int i = 0; i < 15; i++) {
			books.add(mockObject(i));
		}
		return books;
	}
	
}
