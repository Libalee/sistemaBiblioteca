package com.biblioteca.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.controller.UserController;
import com.biblioteca.converter.MyModelMapper;
import com.biblioteca.data.model.Book;
import com.biblioteca.data.model.User;
import com.biblioteca.data.object.UserDO;
import com.biblioteca.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	UserRepository userRepository;
	
	MyModelMapper mapper = new MyModelMapper();
	
	public UserDO create(UserDO userDO) {
		User entity = mapper.parseUserDOToUser(userDO, User.class);
		userRepository.save(entity);
		
		userDO.add(linkTo(methodOn(UserController.class).findById(entity.getId().toString())).withSelfRel());
		
		return userDO;
	}
	
	public UserDO findById(Long Id) {
		var entity = userRepository.findById(Id).orElseThrow();
		UserDO userDO = mapper.parseUserToUserDO(entity, UserDO.class);
		
		userDO.add(linkTo(methodOn(UserController.class).findById(entity.getId().toString())).withSelfRel());
		
		return userDO;
	}
	
	public UserDO update(UserDO userDO) {
		User user = userRepository.findById(userDO.getKey()).orElseThrow();
		
		user.setFineValue(userDO.getFineValue());
		user.setItemsTaken(mapper.parseListBook(userDO.getItemsTaken(), Book.class));
		user.setReservedItems(mapper.parseListBook(userDO.getReservedItems(), Book.class));
		user.setLibraryID(userDO.getLibraryID());
		user.setLibraryPassoword(userDO.getLibraryPassoword());
		user.setName(userDO.getFullName());
		
		UserDO userDO2 = mapper.parseUserToUserDO(userRepository.save(user), UserDO.class);
		
		userDO2.add(linkTo(methodOn(UserController.class).findById(user.getId().toString())).withSelfRel());
		
		return userDO2;
	}
	
	public void delete(Long Id) {
		User user = userRepository.findById(Id).orElseThrow();
		userRepository.delete(user);
	}
	
}
