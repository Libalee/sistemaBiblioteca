package com.biblioteca.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.biblioteca.data.model.Book;
import com.biblioteca.data.model.User;
import com.biblioteca.data.object.BookDO;
import com.biblioteca.data.object.UserDO;

public class MyModelMapper {
	
	// Initializing ModelMapper and the typeMaps
	// For more information read ModelMapper documentation
	// http://modelmapper.org/javadoc/
	// http://modelmapper.org/javadoc/org/modelmapper/TypeMap.html
	ModelMapper mapper = new ModelMapper();
	TypeMap<BookDO, Book> typeMapBookDOToBook = mapper.emptyTypeMap(BookDO.class, Book.class);
	TypeMap<Book, BookDO> typeMapBookToBookDO = mapper.emptyTypeMap(Book.class, BookDO.class);
	TypeMap<UserDO, User> typeMapUserDOToUser = mapper.emptyTypeMap(UserDO.class, User.class);
	TypeMap<User, UserDO> typeMapUserToUserDO = mapper.emptyTypeMap(User.class, UserDO.class);
	
	public MyModelMapper() {
		// Setting up the converter from BookDO to Book
		// Done by linking a getter from BookDO to a setter from Book
		typeMapBookDOToBook.addMapping(BookDO::getAutor, Book::setAutor)
							.addMapping(BookDO::getBorrowingDate, Book::setBorrowingDate)
							.addMapping(BookDO::getReturnDate, Book::setReturnDate)
							.addMapping(BookDO::getName, Book::setName)
							.addMapping(BookDO::getVolume, Book::setVolume)
							.addMapping(BookDO::getId, Book::setId)
							.addMapping(BookDO::isAvaliable, Book::setAvaliable)
							.addMapping(BookDO::isReserved, Book::setReserved);
		
		// Setting up the converter from Book to BookDO
		typeMapBookToBookDO.addMapping(Book::getAutor, BookDO::setAutor)
							.addMapping(Book::getBorrowingDate, BookDO::setBorrowingDate)
							.addMapping(Book::getReturnDate, BookDO::setReturnDate)
							.addMapping(Book::getId, BookDO::setId)
							.addMapping(Book::getName, BookDO::setName) 
							.addMapping(Book::getVolume, BookDO::setVolume)
							.addMapping(Book::isAvaliable, BookDO::setAvaliable)
							.addMapping(Book::isReserved, BookDO::setReserved);
		
		// Setting up the converter from UserDO to User
		typeMapUserDOToUser.addMapping(UserDO::getFullName, User::setName)
							.addMapping(UserDO::getId, User::setId)
							.addMapping(UserDO::getLibraryID, User::setLibraryID)
							.addMapping(UserDO::getLibraryPassoword, User::setLibraryPassoword)
							.addMapping(UserDO::getFineValue, User::setFineValue)
							.addMapping(UserDO::getItemsTaken, User::setItemsTaken)
							.addMapping(UserDO::getReservedItems, User::setReservedItems);
		
		// Setting up the converter from User to UserDO
		typeMapUserToUserDO.addMapping(User::getFirstName, UserDO::setFirstName)
							.addMapping(User::getLastName, UserDO::setLastName)
							.addMapping(User::getId, UserDO::setId)
							.addMapping(User::getLibraryID, UserDO::setLibraryID)
							.addMapping(User::getLibraryPassoword, UserDO::setLibraryPassoword)
							.addMapping(User::getItemsTaken, UserDO::setItemsTaken)
							.addMapping(User::getReservedItems, UserDO::setReservedItems);
							
	}
	
	// Generic Method to convert origin from destination
	public <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	// Method for converting a BookDO to Book
	public Book parseBookDOToBook(BookDO origin, Class<Book> destination) {
		Book book = mapper.map(origin, destination);
		return book;
	}
	
	// Method for converting a Book to BookDO
	public BookDO parseBookToBookDO(Book origin, Class<BookDO> destination) {
		BookDO bookDO = mapper.map(origin, destination);
		return bookDO;
	}
	
	// Method for converting a UserDO to User
	public User parseUserDOToUser(UserDO origin, Class<User> destination) {
		User user = mapper.map(origin, destination);
		return user;
	}
	
	// Method for converting a User to UserDO
	public UserDO parseUserToUserDO(User origin, Class<UserDO> destination) {
		UserDO userDO = mapper.map(origin, destination);
		return userDO;
	}
	
	// Method for converting a generic List of O to a generic list of D
	public <O, D> List<D> parseList(List<O> origin, Class<D> destination) {
		List<D> list = new ArrayList<>();
		for(O o: origin) {
			list.add(parseObject(o, destination));
		}
		return list;
	}
	
	// Method for converting a List of BookDO to List of Book
	public List<Book> parseListBook(List<BookDO> origin, Class<Book> destination) {
		List<Book> books = new ArrayList<>();
		if(origin != null) {
			for(BookDO bookDO: origin) {
				books.add(parseBookDOToBook(bookDO, destination));
			}
			return books;
		}
		return null;
	}
	
	// Method for converting a List of Book to List of BookDO
	public List<BookDO> parseListBookDO(List<Book> origin, Class<BookDO> destination) {
		List<BookDO> bookDOs = new ArrayList<>();
		if(origin != null) {
			for(Book book: origin) {
				bookDOs.add(parseBookToBookDO(book, destination));
			}
			return bookDOs;
		}
		return null;
	}
	
	// Method for converting a List of UserDO to List of User
	public List<User> parseListUser(List<UserDO> origin, Class<User> destination) {
		List<User> users = new ArrayList<>();
		if(origin != null) {
			for(UserDO userDO: origin) {
				users.add(parseUserDOToUser(userDO, destination));
			}
			return users;
		}
		return null;
	}
	
	// Method for converting a List of User to List of UserDO
	public List<UserDO> parseListUserDO(List<User> origin, Class<UserDO> destination) {
		if(origin != null) {
			List<UserDO> userDOs = new ArrayList<>();
			for(User user: origin) {
				userDOs.add(parseUserToUserDO(user, destination));
			}
			return userDOs;
		}
		return null;
	}
	
	
}
