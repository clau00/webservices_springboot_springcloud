package com.learning.rest.webservices.restfulwebservices.user;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;

import org.hibernate.EntityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);

		if (user == null) {
			throw new UserNotFoundException("id - " + id);
		}

		EntityModel<User> model = EntityModel.of(user);

		WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));

		return model;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);

		// setting status of CREATED = 201
		// setting the location as: /users/{id}
		// get /users fromCurrentRequest() and adding /{id} with .path()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userDaoService.delete(id);

		if(user == null) {
			throw new UserNotFoundException("id - " + id);
		}
	}

}
