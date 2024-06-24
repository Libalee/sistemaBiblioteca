package com.biblioteca.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.converter.MathConverter;
import com.biblioteca.data.object.BookDO;
import com.biblioteca.services.BookServices;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	BookServices bookServices;
	
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public Page<BookDO> findAll(
				@RequestParam(value= "page", defaultValue = "0") int page,
				@RequestParam(value= "limit", defaultValue = "20") int limit,
				@RequestParam(value= "direction", defaultValue = "asc") String direction
			) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
		
		Page<BookDO> bookDOs = bookServices.findAll(pageable);
		
		bookDOs.stream().forEach(entity -> entity.add(linkTo(methodOn(BookController.class).
				findById(entity.getKey().toString())).withSelfRel()));
		
		return bookDOs;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookDO findById(@PathVariable("id") String id) {
		var entity = bookServices.findbyID(MathConverter.convertStringToLong(id));
		entity.add(linkTo(methodOn(BookController.class).findById(entity.getKey().toString())).withSelfRel());
		return entity;
	}
	
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookDO create(@RequestBody BookDO bookDO) {
		var entity = bookServices.create(bookDO);
		entity.add(linkTo(methodOn(BookController.class).findById(entity.getKey().toString())).withSelfRel());
		return entity;
	}
	
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookDO uptade(@RequestBody BookDO bookDO) {
		var entity = bookServices.update(bookDO);
		entity.add(linkTo(methodOn(BookController.class).findById(entity.getKey().toString())).withSelfRel());
		return entity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		bookServices.delete(MathConverter.convertStringToLong(id));
		return ResponseEntity.noContent().build();
	}
	
}
