package com.biblioteca.unitTests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.data.model.Book;
import com.biblioteca.data.object.BookDO;
import com.biblioteca.repository.BookRepository;
import com.biblioteca.services.BookServices;
import com.biblioteca.unitTests.converter.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {
	
	MockBook mockBook;
	
	@InjectMocks
	private BookServices services;
	
	@Mock
	private BookRepository repository;
	
	int randomNumber;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		mockBook = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreate() {
		randomNumber = (int) (10 * Math.random());
		Book book = mockBook.mockModel(randomNumber);
		BookDO bookDO = mockBook.mockObject(randomNumber);
		
		when(repository.save(book)).thenReturn(book);
		
		var result = services.create(bookDO);
		
		assertNotNull(result);
		assertEquals(book.getAutor(), result.getAutor());
		assertEquals(book.getBorrowingDate(), result.getBorrowingDate());
		assertEquals(book.getId(), result.getKey());
		assertEquals(book.getName(), result.getName());
		assertEquals(book.getReturnDate(), result.getReturnDate());
		assertEquals(book.getVolume(), result.getVolume());
	}

	@Test
	void testFindbyID() {
		randomNumber = (int) (10 * Math.random());
		Book book = mockBook.mockModel(randomNumber);
		
		when(repository.findById(book.getId())).thenReturn(Optional.of(book));
		
		var result = services.findbyID(book.getId());
		
		assertNotNull(result);
		assertEquals(book.getAutor(), result.getAutor());
		assertEquals(book.getBorrowingDate(), result.getBorrowingDate());
		assertEquals(book.getId(), result.getKey());
		assertEquals(book.getName(), result.getName());
		assertEquals(book.getReturnDate(), result.getReturnDate());
		assertEquals(book.getVolume(), result.getVolume());
	}

	@Test
	void testUptade() {
		randomNumber = (int) (10 * Math.random());
		Book book = mockBook.mockModel(randomNumber);
		BookDO bookDO = mockBook.mockObject(randomNumber);
		
		when(repository.findById(bookDO.getKey())).thenReturn(Optional.of(book));
		when(repository.save(book)).thenReturn(book);
		
		var result = services.update(bookDO);
		
		assertNotNull(result);
		assertEquals(book.getAutor(), result.getAutor());
		assertEquals(book.getBorrowingDate(), result.getBorrowingDate());
		assertEquals(book.getId(), result.getKey());
		assertEquals(book.getName(), result.getName());
		assertEquals(book.getReturnDate(), result.getReturnDate());
		assertEquals(book.getVolume(), result.getVolume());
	}

	@Test
	void testDelete() {
		randomNumber = (int) (10 * Math.random());
		Book book = mockBook.mockModel(randomNumber);
		BookDO bookDO = mockBook.mockObject(randomNumber);
		
		when(repository.findById(bookDO.getKey())).thenReturn(Optional.of(book));
		
		services.delete(book.getId());
	}

}
