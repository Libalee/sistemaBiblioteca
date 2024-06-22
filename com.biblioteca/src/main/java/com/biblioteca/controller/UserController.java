package com.biblioteca.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

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
import com.biblioteca.data.object.BookDO;
import com.biblioteca.data.object.UserDO;
import com.biblioteca.services.BookServices;
import com.biblioteca.services.UserServices;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

	@Autowired
	UserServices userServices;
	@Autowired
	BookServices bookServices;
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public UserDO findById(@PathVariable("id") String id) {
		var entity = userServices.findById(MathConverter.convertStringToLong(id));
		entity.add(linkTo(methodOn(UserController.class).findById(entity.getKey().toString())).withSelfRel());
		// Adds link references for the books contained in the User object
		if(entity.getItemsTaken() == null) {
			for(BookDO bookDO: entity.getItemsTaken()) {
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				
			}
		}
		// Adds link references for the books contained in the User object
		if(entity.getReservedItems() == null) {
			for(BookDO bookDO: entity.getReservedItems()) {
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				
			}
		}
		return entity;
	}
	
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public UserDO create(@RequestBody UserDO userDO) {
		var entity = userServices.create(userDO);
		entity.add(linkTo(methodOn(UserController.class).findById(entity.getKey().toString())).withSelfRel());
		// Adds link references for the books contained in the User object
		if(entity.getItemsTaken() == null) {
			for(BookDO bookDO: entity.getItemsTaken()) {
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				
			}
		}
		// Adds link references for the books contained in the User object
		if(entity.getReservedItems() == null) {
			for(BookDO bookDO: entity.getReservedItems()) {
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				
			}
		}
		return entity;
	}
	
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public UserDO uptade(@RequestBody UserDO userDO) {
		var entity = userServices.update(userDO);
		entity.add(linkTo(methodOn(UserController.class).findById(entity.getKey().toString())).withSelfRel());
		// Adds link references for the books contained in the User object
		if(entity.getItemsTaken() == null) {
			for(BookDO bookDO: entity.getItemsTaken()) {
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				
			}
		}
		// Adds link references for the books contained in the User object
		if(entity.getReservedItems() == null) {
			for(BookDO bookDO: entity.getReservedItems()) {
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				
			}
		}
		
		return entity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		userServices.delete(MathConverter.convertStringToLong(id));
		return ResponseEntity.noContent().build();
	}
	
	// TO-DO add a try catch in case the userDO contains an empty list and sends a bad request error
	@PutMapping(value = "/borrow", produces = {"application/json", "application/xml", "application/x-yaml"},
							consumes = {"application/json", "application/xml", "application/x-yaml"})
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
				// Adds link references for the books contained in the User object
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				bookServices.update(bookDO);
			}
			
		} else {
			
			for(BookDO bookDO: entity.getItemsTaken()) {
				list.remove(bookDO);
			}
			entity.borrowBook(list);
			for(BookDO bookDO : list) {
				// Adds link references for the books contained in the User object
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				bookServices.update(bookDO);
			}
		}	
		
		entity.add(linkTo(methodOn(UserController.class).findById(entity.getKey().toString())).withSelfRel());
		return userServices.update(entity);
	}
	
	// TO-DO add a try catch in case the entity contains an empty list and sends a bad request error
	@PutMapping(value = "/return", produces = {"application/json", "application/xml", "application/x-yaml"},
									consumes = {"application/json", "application/xml", "application/x-yaml"})
	public UserDO returnBook(@RequestBody UserDO userDO) {
		
		UserDO entity = userServices.findById(userDO.getKey());
		// Gets the items given in the body and allocates them in a list
		List<BookDO> list = userDO.getItemsTaken();
		// Uses the list to return the books and saves the returned books in another list
		List<BookDO> returnedBooks = entity.returnBook(list);
			
		// Updates the returned books in the database
		for(BookDO bookDO: returnedBooks) {
			bookServices.update(bookDO);
		}	
		// Adds link references for the books contained in the User object
		if(entity.getItemsTaken() == null) {
			for(BookDO bookDO: entity.getItemsTaken()) {
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				
			}
		}
		// Adds link references for the books contained in the User object
		if(entity.getReservedItems() == null) {
			for(BookDO bookDO: entity.getReservedItems()) {
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
			}
		}
		
		return userServices.update(entity);
	}
	
	@PutMapping(value = "/reserve", produces = {"application/json", "application/xml", "application/x-yaml"},
									consumes = {"application/json", "application/xml", "application/x-yaml"})
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
				// Adds link references for the books contained in the User object
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				bookServices.update(bookDO);
			}
					
		} else {
					
			for(BookDO bookDO: entity.getItemsTaken()) {
				list.remove(bookDO);
			}
				entity.reserveBook(list);
			for(BookDO bookDO : list) {
				// Adds link references for the books contained in the User object
				entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				bookServices.update(bookDO);
			}
		}
		return userServices.update(entity);
	}
	
	// TO-DO add a try catch in case the entity contains an empty list and sends a bad request error
		@PutMapping(value = "/unreserve", produces = {"application/json", "application/xml", "application/x-yaml"},
											consumes = {"application/json", "application/xml", "application/x-yaml"})
		public UserDO unReserveBook(@RequestBody UserDO userDO) {
			
			UserDO entity = userServices.findById(userDO.getKey());
			
			List<BookDO> list = userDO.getReservedItems();
			List<BookDO> returnedBooks = entity.unReserveBook(list);
				
			for(BookDO bookDO: returnedBooks) {
				bookServices.update(bookDO);
			}	
			
			// Adds link references for the books contained in the User object
			if(entity.getItemsTaken() == null) {
				for(BookDO bookDO: entity.getItemsTaken()) {
					entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
					
				}
			}
			// Adds link references for the books contained in the User object
			if(entity.getReservedItems() == null) {
				for(BookDO bookDO: entity.getReservedItems()) {
					entity.add(linkTo(methodOn(BookController.class).findById(bookDO.getKey().toString())).withSelfRel());
				}
			}
			
			return userServices.update(entity);
		}
	
}
