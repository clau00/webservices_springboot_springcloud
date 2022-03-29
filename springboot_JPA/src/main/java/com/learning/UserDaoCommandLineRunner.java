package com.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learning.jpa.entity.User;
import com.learning.jpa.service.UserDAOService;

@Component
public class UserDaoCommandLineRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoCommandLineRunner.class);

	@Autowired
	private UserDAOService userDAOService;

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jack", "Admin");
		userDAOService.insert(user);
		logger.info("New User is created: " + user);
	}
}
