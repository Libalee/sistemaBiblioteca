package com.biblioteca.unitTests.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.biblioteca.converter.MyModelMapper;
import com.biblioteca.data.model.Book;
import com.biblioteca.data.model.User;
import com.biblioteca.data.object.BookDO;
import com.biblioteca.data.object.UserDO;

@TestInstance(Lifecycle.PER_CLASS)
class MyModelMapperTest {
	
	private MockBook mockBook;
	private MockUser mockUser;
	private MyModelMapper mapper;
	int randomNumber;
	
	@BeforeAll
	void setUpBeforeClass() throws Exception {
		mockUser = new MockUser();
		mockBook = new MockBook();
		mapper = new MyModelMapper();
	}

	@Test
	void testParseBookDOToBook() {
		randomNumber = (int) (10 * Math.random());
		BookDO bookDO = mockBook.mockObject(randomNumber);
		Book book = mapper.parseBookDOToBook(bookDO, Book.class);
		
		assertEquals(bookDO.getAutor(), book.getAutor());
		assertEquals(bookDO.getBorrowingDate(), book.getBorrowingDate());
		assertEquals(bookDO.getKey(), book.getId());
		assertEquals(bookDO.getName(), book.getName());
		assertEquals(bookDO.getReturnDate(), book.getReturnDate());
		assertEquals(bookDO.getVolume(), book.getVolume());
	}

	@Test
	void testParseBookToBookDO() {
		randomNumber = (int) (10 * Math.random());
		Book book = mockBook.mockModel(randomNumber);
		BookDO bookDO = mapper.parseBookToBookDO(book, BookDO.class);
		
		assertEquals(book.getAutor(), bookDO.getAutor());
		assertEquals(book.getBorrowingDate(), bookDO.getBorrowingDate());
		assertEquals(book.getId(), bookDO.getKey());
		assertEquals(book.getName(), bookDO.getName());
		assertEquals(book.getReturnDate(), bookDO.getReturnDate());
		assertEquals(book.getVolume(), bookDO.getVolume());
	}

	@Test
	void testParseUserDOToUser() {
		randomNumber = (int) (10 * Math.random());
		UserDO userDO = mockUser.mockObject(randomNumber);
		User user = mapper.parseUserDOToUser(userDO, User.class);
		
		assertEquals(userDO.getFineValue(), user.getFineValue());
		assertEquals(userDO.getFirstName(), user.getFirstName());
		assertEquals(userDO.getLastName(), user.getLastName());
		assertEquals(userDO.getItemsTaken(), user.getItemsTaken());
		assertEquals(userDO.getKey(), user.getId());
		assertEquals(userDO.getLibraryID(), user.getLibraryID());
		assertEquals(userDO.getLibraryPassoword(), user.getLibraryPassoword());
		assertEquals(userDO.getReservedItems(), user.getReservedItems());
		
	}

	@Test
	void testParseUserToUserDO() {
		randomNumber = (int) (10 * Math.random());
		User user = mockUser.mockModel(randomNumber);
		UserDO userDO = mapper.parseUserToUserDO(user, UserDO.class);
		
		assertEquals(user.getFineValue(), userDO.getFineValue());
		assertEquals(user.getFirstName(), userDO.getFirstName());
		assertEquals(user.getLastName(), userDO.getLastName());
		assertEquals(user.getItemsTaken(), userDO.getItemsTaken());
		assertEquals(user.getId(), userDO.getKey());
		assertEquals(user.getLibraryID(), userDO.getLibraryID());
		assertEquals(user.getLibraryPassoword(), userDO.getLibraryPassoword());
		assertEquals(user.getReservedItems(), userDO.getReservedItems());
	}


	@Test
	void testParseListBook() {
		randomNumber = (int) (10 * Math.random());
		List<BookDO> bookDOs = mockBook.mockObjectList(randomNumber);
		List<Book> books = mapper.parseListBook(bookDOs, Book.class);
		
		for(int i = 0; i < bookDOs.size(); i++) {
			BookDO bookDO = bookDOs.get(i);
			Book book = books.get(i);
			assertEquals(bookDO.getAutor(), book.getAutor());
			assertEquals(bookDO.getBorrowingDate(), book.getBorrowingDate());
			assertEquals(bookDO.getKey(), book.getId());
			assertEquals(bookDO.getName(), book.getName());
			assertEquals(bookDO.getReturnDate(), book.getReturnDate());
			assertEquals(bookDO.getVolume(), book.getVolume());
		}
		
	}

	@Test
	void testParseListBookDO() {
		randomNumber = (int) (10 * Math.random());
		List<Book> books = mockBook.mockModelList(randomNumber);
		List<BookDO> bookDOs = mapper.parseListBookDO(books, BookDO.class);
		
		for(int i = 0; i < books.size(); i++) {
			BookDO bookDO = bookDOs.get(i);
			Book book = books.get(i);
			assertEquals(bookDO.getAutor(), book.getAutor());
			assertEquals(bookDO.getBorrowingDate(), book.getBorrowingDate());
			assertEquals(bookDO.getKey(), book.getId());
			assertEquals(bookDO.getName(), book.getName());
			assertEquals(bookDO.getReturnDate(), book.getReturnDate());
			assertEquals(bookDO.getVolume(), book.getVolume());
		}
	}

	@Test
	void testParseListUser() {
		randomNumber = (int) (10 * Math.random());
		List<UserDO> userDOs = mockUser.mockObjectList(randomNumber);
		List<User> users = mapper.parseListUser(userDOs, User.class);
		
		for(int i = 0; i < userDOs.size(); i++) {
			UserDO userDO = userDOs.get(i);
			User user = users.get(i);
			
			assertEquals(userDO.getFineValue(), user.getFineValue());
			assertEquals(userDO.getFirstName(), user.getFirstName());
			assertEquals(userDO.getLastName(), user.getLastName());
			assertEquals(userDO.getItemsTaken(), user.getItemsTaken());
			assertEquals(userDO.getKey(), user.getId());
			assertEquals(userDO.getLibraryID(), user.getLibraryID());
			assertEquals(userDO.getLibraryPassoword(), user.getLibraryPassoword());
			assertEquals(userDO.getReservedItems(), user.getReservedItems());
		}
		
	}

	@Test
	void testParseListUserDO() {
		randomNumber = (int) (10 * Math.random());
		List<User> users = mockUser.mockModelList(randomNumber);
		List<UserDO> userDOs = mapper.parseListUserDO(users, UserDO.class);
		
		for(int i = 0; i < users.size(); i++) {
			UserDO userDO = userDOs.get(i);
			User user = users.get(i);
			
			assertEquals(userDO.getFineValue(), user.getFineValue());
			assertEquals(userDO.getFirstName(), user.getFirstName());
			assertEquals(userDO.getLastName(), user.getLastName());
			assertEquals(userDO.getItemsTaken(), user.getItemsTaken());
			assertEquals(userDO.getKey(), user.getId());
			assertEquals(userDO.getLibraryID(), user.getLibraryID());
			assertEquals(userDO.getLibraryPassoword(), user.getLibraryPassoword());
			assertEquals(userDO.getReservedItems(), user.getReservedItems());
		}
	}

}
