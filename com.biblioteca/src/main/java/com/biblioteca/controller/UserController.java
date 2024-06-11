package com.biblioteca.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.converter.MathConverter;
import com.biblioteca.data.model.Book;
import com.biblioteca.data.object.BookDO;
import com.biblioteca.data.object.UserDO;
import com.biblioteca.services.BookServices;
import com.biblioteca.services.UserServices;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

	@Autowired
	UserServices userServices;
	@Autowired
	BookServices bookServices;
	
	@GetMapping(value = "/{id}")
	public UserDO findById(@PathVariable("id") String id) {
		return userServices.findById(MathConverter.convertStringToLong(id));
	}
	
	@PostMapping
	public UserDO create(@RequestBody UserDO userDO) {
		var entity = userDO;
		userServices.create(userDO);
		return entity;
	}
	
	@PutMapping
	public UserDO uptade(@RequestBody UserDO userDO) {
		var entity = userServices.update(userDO);
		return entity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		userServices.delete(MathConverter.convertStringToLong(id));
		return ResponseEntity.noContent().build();
	}
	
	// TO-DO add a try catch in case the userDO contains an empty list and sends a bad request error
	@PutMapping("/borrow")
	public UserDO borrowBook(@RequestBody UserDO userDO) {
		
		UserDO entity = userServices.findById(userDO.getKey());
		
		List<BookDO> list = userDO.getItemsTaken();
		// Checks if the list in entity is null
		// if it's null, just adds the BookDOs to the entity
		// if it's not null, it removes all of the repeated BookDOs before adding them the entity
		if(entity.getItemsTaken() == null) {
			List<BookDO> l = new ArrayList<>();
			entity.setItemsTaken(l);
			entity.borrowBook(list);
			
			for(BookDO bookDO: list) {
				bookServices.uptade(bookDO);
			}
			
		} else {
			
			for(BookDO bookDO: entity.getItemsTaken()) {
				list.remove(bookDO);
			}
			entity.borrowBook(list);
			for(BookDO bookDO : list) {
				bookServices.uptade(bookDO);
			}
		}	
		
		return userServices.update(entity);
	}
	
	// TO-DO add a try catch in case the entity contains an empty list and sends a bad request error
	@PutMapping("/return")
	public UserDO returnBook(@RequestBody UserDO userDO) {
		
		UserDO entity = userServices.findById(userDO.getKey());
		// Gets the items given in the body and allocates them in a list
		List<BookDO> list = userDO.getItemsTaken();
		// Uses the list to return the books and saves the returned books in another list
		List<BookDO> returnedBooks = entity.returnBook(list);
			
		// Updates the returned books in the database
		for(BookDO bookDO: returnedBooks) {
			bookServices.uptade(bookDO);
		}	
		
		return userServices.update(entity);
	}
	
	@PutMapping("/reserve")
	public UserDO reserveBook(@RequestBody UserDO userDO) {
		
		UserDO entity = userServices.findById(userDO.getKey());
		
		List<BookDO> list = userDO.getReservedItems();
		// Checks if the list in entity is null
		// if it's null, just adds the BookDOs to the entity
		// if it's not null, it removes all of the repeated BookDOs before adding them the entity
		if(entity.getReservedItems() == null) {
			List<BookDO> l = new ArrayList<>();
		 	entity.setItemsTaken(l);
			entity.reserveBook(list);
				
			for(BookDO bookDO: list) {
				bookServices.uptade(bookDO);
			}
					
		} else {
					
			for(BookDO bookDO: entity.getItemsTaken()) {
				list.remove(bookDO);
			}
				entity.reserveBook(list);
			for(BookDO bookDO : list) {
				bookServices.uptade(bookDO);
			}
		}
		return userServices.update(entity);
	}
	
	// TO-DO add a try catch in case the entity contains an empty list and sends a bad request error
		@PutMapping("/reserve/unreserve")
		public UserDO unReserveBook(@RequestBody UserDO userDO) {
			
			UserDO entity = userServices.findById(userDO.getKey());
			
			List<BookDO> list = userDO.getReservedItems();
			List<BookDO> returnedBooks = entity.unReserveBook(list);
				
			for(BookDO bookDO: returnedBooks) {
				bookServices.uptade(bookDO);
			}	
			
			return userServices.update(entity);
		}
	
}
