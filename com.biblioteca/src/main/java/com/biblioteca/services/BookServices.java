package com.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.converter.MyModelMapper;
import com.biblioteca.repository.BookRepository;

@Service
public class BookServices {

	@Autowired
	BookRepository bookRepository;
	
	MyModelMapper mapper = new MyModelMapper();
	
}
