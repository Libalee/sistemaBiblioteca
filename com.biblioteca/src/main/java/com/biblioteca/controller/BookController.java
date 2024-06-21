package com.biblioteca.controller;

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
import com.biblioteca.services.BookServices;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	BookServices bookServices;
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookDO findById(@PathVariable("id") String id) {
		var entity = bookServices.findbyID(MathConverter.convertStringToLong(id));
		return entity;
	}
	
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookDO create(@RequestBody BookDO bookDO) {
		var entity = bookServices.create(bookDO);
		return entity;
	}
	
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookDO uptade(@RequestBody BookDO bookDO) {
		var entity = bookServices.update(bookDO);
		return entity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		bookServices.delete(MathConverter.convertStringToLong(id));
		return ResponseEntity.noContent().build();
	}
	
}
