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
		var entity = userDO;
		userServices.update(userDO);
		return entity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		userServices.delete(MathConverter.convertStringToLong(id));
		return ResponseEntity.noContent().build();
	}
	
}
