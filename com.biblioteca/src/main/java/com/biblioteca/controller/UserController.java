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
import com.biblioteca.services.UserServices;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

	@Autowired
	UserServices userServices;
	
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
			for(BookDO bookDO : list) {
				entity.borrowBook(bookDO);
			}
		} else {
			list.removeAll(entity.getItemsTaken());
			
			for(BookDO bookDO : list) {
				entity.borrowBook(bookDO);
			}
		}	
		
		return userServices.update(entity);
	}
	
	// TO-DO add a try catch in case the entity contains an empty list and sends a bad request error
	@PutMapping("/return")
	public UserDO returnBook(@RequestBody UserDO userDO) {
		
		UserDO entity = userServices.findById(userDO.getKey());
		
		List<BookDO> list = entity.getItemsTaken();
		// Checks if the list in userDO is null
		// if it's null, just removes the BookDOs from the userDO
		// if it's not null, it removes specific books from the userDO
		if(userDO.getItemsTaken() == null) {
			for(BookDO bookDO : list) {
				List<BookDO> l = new ArrayList<>();
				userDO.setItemsTaken(l);
				userDO.returnBook(bookDO);
			}
		} else {
			list.removeAll(userDO.getItemsTaken());
			
			for(BookDO bookDO : list) {
				userDO.returnBook(bookDO);
			}
		}
		
		uptade(userDO);
		
		return userDO;
	}
	
}
