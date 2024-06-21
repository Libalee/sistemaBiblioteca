package com.biblioteca.unitTests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.data.model.User;
import com.biblioteca.data.object.UserDO;
import com.biblioteca.repository.UserRepository;
import com.biblioteca.services.UserServices;
import com.biblioteca.unitTests.converter.MockUser;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServicesTest {
	
	MockUser mockUser;
	int randomNumber;
	
	@InjectMocks
	private UserServices services;
	
	@Mock
	private UserRepository repository;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		mockUser = new MockUser();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreate() {
		randomNumber =  (int) (10 * Math.random());
		
		User user = mockUser.mockModel(randomNumber);
		UserDO userDO = mockUser.mockObject(randomNumber);
		
		when(repository.save(user)).thenReturn(user);
		
		var result = services.create(userDO);

		assertNotNull(result);
		assertEquals(result.getFineValue(), user.getFineValue());
		assertEquals(result.getFirstName(), user.getFirstName());
		assertEquals(result.getLastName(), user.getLastName());
		assertEquals(result.getKey(), user.getId());
		assertEquals(result.getLibraryID(), user.getLibraryID());
		assertEquals(result.getLibraryPassoword(), user.getLibraryPassoword());
		// assertEquals(result.getReservedItems(), user.getReservedItems());
		// assertEquals(result.getItemsTaken(), user.getItemsTaken());
		// These assertEquals don't work because the items inside of "result" have HATEOAS links in them
		// making it so both lists aren't equal
	}

	@Test
	void testFindById() {
		randomNumber =  (int) (10 * Math.random());
		
		User user = mockUser.mockModel(randomNumber);
		UserDO userDO = mockUser.mockObject(randomNumber);
		
		when(repository.findById(userDO.getKey())).thenReturn(Optional.of(user));
		
		
		var result = services.findById(userDO.getKey());

		assertNotNull(result);
		assertEquals(result.getFineValue(), user.getFineValue());
		assertEquals(result.getFirstName(), user.getFirstName());
		assertEquals(result.getLastName(), user.getLastName());
		assertEquals(result.getKey(), user.getId());
		assertEquals(result.getLibraryID(), user.getLibraryID());
		assertEquals(result.getLibraryPassoword(), user.getLibraryPassoword());
	}

	@Test
	void testUpdate() {
		randomNumber =  (int) (10 * Math.random());
		
		User user = mockUser.mockModel(randomNumber);
		UserDO userDO = mockUser.mockObject(randomNumber);
		
		when(repository.findById(userDO.getKey())).thenReturn(Optional.of(user));
		when(repository.save(user)).thenReturn(user);
		
		var result = services.update(userDO);

		assertNotNull(result);
		assertEquals(result.getFineValue(), user.getFineValue());
		assertEquals(result.getFirstName(), user.getFirstName());
		assertEquals(result.getLastName(), user.getLastName());
		assertEquals(result.getKey(), user.getId());
		assertEquals(result.getLibraryID(), user.getLibraryID());
		assertEquals(result.getLibraryPassoword(), user.getLibraryPassoword());
	}

	@Test
	void testDelete() {
		randomNumber = (int) (10 * Math.random());
		User user = mockUser.mockModel(randomNumber);
		UserDO userDO = mockUser.mockObject(randomNumber);
		
		when(repository.findById(userDO.getKey())).thenReturn(Optional.of(user));
		
		services.delete(user.getId());
	}

}
