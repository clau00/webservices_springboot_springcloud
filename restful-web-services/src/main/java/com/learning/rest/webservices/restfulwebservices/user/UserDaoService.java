package com.learning.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;

	static {
		users.add(new User(1, "Jack", new Date()));
		users.add(new User(2, "Jill", new Date()));
		users.add(new User(3, "John", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if(user.getId() == 0) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}

}
