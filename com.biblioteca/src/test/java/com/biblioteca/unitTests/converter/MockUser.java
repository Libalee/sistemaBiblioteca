package com.biblioteca.unitTests.converter;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.data.model.User;
import com.biblioteca.data.object.UserDO;

public class MockUser {
	
	public MockUser() {
		
	}
	
	public User mockModel(Integer integer) {
		User user = new User();
		MockBook book = new MockBook();
		user.setName("Very Cool Name " + integer);
		user.setFineValue(integer + 100.00);
		user.setId(integer + 10L);
		user.setLibraryID("user_name_" + integer);
		user.setLibraryPassoword("strong_password_" + integer);
		user.setItemsTaken(book.mockModelList(integer));
		user.setReservedItems(book.mockModelList(integer));
		
		return user;
	}
	
	public UserDO mockObject(Integer integer) {
		UserDO user = new UserDO();
		MockBook book = new MockBook();
		user.setFirstName("Very");
		user.setLastName("Cool Name " + integer);
		user.setFineValue(integer + 100.00);
		user.setKey(integer + 10L);
		user.setLibraryID("user_name_" + integer);
		user.setLibraryPassoword("strong_password_" + integer);
		user.setItemsTaken(book.mockObjectList(integer));
		user.setReservedItems(book.mockObjectList(integer));
		
		return user;
	}
	
	public User mockModel() {
		return mockModel(0);
	}
	
	public UserDO mockObject() {
		return mockObject(0);
	}
	
	public List<User> mockModelList(Integer integer) {
		List<User> users = new ArrayList<>();
		
		for(int i = 0; i < integer; i++) {
			users.add(mockModel(i));
		}
		return users;
	}
	
	public List<UserDO> mockObjectList(Integer integer) {
		List<UserDO> users = new ArrayList<>();
		
		for(int i = 0; i < integer; i++) {
			users.add(mockObject(i));
		}
		return users;
	}
	
	public List<User> mockModelList() {
		List<User> users = new ArrayList<>();
		
		for(int i = 0; i < 15; i++) {
			users.add(mockModel(i));
		}
		return users;
	}
	
	public List<UserDO> mockObjectList() {
		List<UserDO> users = new ArrayList<>();
		
		for(int i = 0; i < 15; i++) {
			users.add(mockObject(i));
		}
		return users;
	}
	
}
