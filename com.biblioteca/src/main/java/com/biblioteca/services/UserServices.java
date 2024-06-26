package com.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	
	public Page<UserDO> findAll(Pageable pageable) {
		var page = userRepository.findAll(pageable);
		return page.map(entityUser -> mapper.parseUserToUserDO(entityUser, UserDO.class));
	}
	
	public UserDO create(UserDO userDO) {
		User entity = mapper.parseUserDOToUser(userDO, User.class);
		userRepository.save(entity);

		return userDO;
	}
	
	public UserDO findById(Long Id) {
		var entity = userRepository.findById(Id).orElseThrow();
		UserDO userDO = mapper.parseUserToUserDO(entity, UserDO.class);

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
		
		userRepository.save(user);

		return userDO;
	}
	
	public void delete(Long Id) {
		User user = userRepository.findById(Id).orElseThrow();
		userRepository.delete(user);
	}
	
	
}
