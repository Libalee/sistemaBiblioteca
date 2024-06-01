package com.biblioteca.testMyModelMapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class MyModelMapper {
	
	ModelMapper mapper = new ModelMapper();
	TypeMap<BookDO, Book> typeMapBookDOToBook = mapper.emptyTypeMap(BookDO.class, Book.class);
	
	public MyModelMapper() {
		typeMapBookDOToBook.addMapping(BookDO::getAutor, Book::setAutor)
							.addMapping(BookDO::getBorrowingDate, Book::setBorrowingDate)
							.addMapping(BookDO::getReturnDate, Book::setReturnDate)
							.addMapping(BookDO::getName, Book::setName)
							.addMapping(BookDO::getVolume, Book::setVolume)
							.addMapping(BookDO::getId, Book::setId)
							.addMapping(BookDO::isAvaliable, Book::setAvaliable)
							.addMapping(BookDO::isReserved, Book::setReserved);
	}
	
	public Book parseBookDOToBook(BookDO origin, Class<Book> destination) {
		Book book = mapper.map(origin, destination);
		return book;
	}
}
