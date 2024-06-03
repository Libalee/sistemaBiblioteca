package com.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.converter.MyModelMapper;
import com.biblioteca.data.model.Book;
import com.biblioteca.data.object.BookDO;
import com.biblioteca.repository.BookRepository;

@Service
public class BookServices {

	@Autowired
	BookRepository bookRepository;
	
	MyModelMapper mapper = new MyModelMapper();
	
	public BookDO create(BookDO bookDO) {
		Book entity = mapper.parseBookDOToBook(bookDO, Book.class);
		bookRepository.save(entity);
		return bookDO;
	}
	
	public BookDO findbyID(Long Id) {
		var entity = bookRepository.findById(Id).orElseThrow();
		BookDO bookDO = mapper.parseBookToBookDO(entity, BookDO.class);
		return bookDO;
	}
	
	public BookDO uptade(BookDO bookDO) {
		Book book = bookRepository.getReferenceById(bookDO.getId());
		
		book.setAutor(bookDO.getAutor());
		book.setAvaliable(bookDO.isAvaliable());
		book.setBorrowingDate(bookDO.getBorrowingDate());
		book.setName(bookDO.getName());;
		book.setReserved(bookDO.isReserved());
		book.setReturnDate(bookDO.getReturnDate());
		book.setVolume(bookDO.getVolume());
		
		
		return bookDO;
	}
	
	public void Delete(Long id) {
		Book entity = bookRepository.findById(id).orElseThrow();
		bookRepository.delete(entity);
	}
	
}
