package com.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.converter.MyModelMapper;
import com.biblioteca.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	UserRepository repository;
	
	MyModelMapper mapper = new MyModelMapper();
	
	
}
