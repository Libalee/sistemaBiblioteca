package com.biblioteca.testMyModelMapper;

import com.biblioteca.data.model.User;
import com.biblioteca.data.object.UserDO;

public class test {
	public static void main(String[] args) {
		
		
		MyModelMapper mapper = new MyModelMapper();
		
		UserDO user = new UserDO(1L, "Carolina", "Alvino", "Carol_Alvino", "senha3vezes", null, null, 0.0);
		
		User user2 = mapper.parseUserDOToUser(user, User.class);
		
		System.out.println(user2.getLastName());
	}
}
