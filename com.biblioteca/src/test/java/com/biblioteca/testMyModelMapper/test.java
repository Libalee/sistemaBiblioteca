package com.biblioteca.testMyModelMapper;

import java.sql.Date;

public class test {
	public static void main(String[] args) {
		
		BookDO bookDO = new BookDO(1L, "Curso de Analise", "Elon Lages Lima", 1, true, new Date(0), new Date(0), false);
		
		MyModelMapper mapper = new MyModelMapper();
		
		Book book = mapper.parseBookDOToBook(bookDO, Book.class);
		
		System.out.println(book.getAutor());
		System.out.println(book.getName());
		System.out.println(book.getId());
		System.out.println(book.getVolume());
		System.out.println(book.getBorrowingDate());
		System.out.println(book.getReturnDate());
		
	}

}
