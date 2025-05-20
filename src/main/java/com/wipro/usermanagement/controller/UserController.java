package com.wipro.usermanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.usermanagement.dto.LoginRequest;
import com.wipro.usermanagement.dto.UserDetails;
import com.wipro.usermanagement.entity.User;
import com.wipro.usermanagement.exception.DuplicateUserException;
import com.wipro.usermanagement.exception.InvalidCredentialsException;
import com.wipro.usermanagement.exception.UserNotFoundException;
import com.wipro.usermanagement.response.ApiResponse;
import com.wipro.usermanagement.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws InvalidCredentialsException {

		ApiResponse successResponse = userService.userLogin(loginRequest.getEmailId(), loginRequest.getPassword());
//		if ("admin".equals(loginRequest.getEmailId()) && "password".equals(loginRequest.getPassword())) {

		
		return ResponseEntity.ok().body(successResponse);
	}

	@PostMapping("/register")
	public ResponseEntity<String> addUser(@RequestBody UserDetails userDetails) throws DuplicateUserException {

		userService.addUser(userDetails);
		return ResponseEntity.ok("User deatils added successfully");
	}

	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody UserDetails userDetails) throws UserNotFoundException {

		userService.updateUser(userDetails);
		return ResponseEntity.ok("User deatils updated successfully");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(int id) throws UserNotFoundException {

		userService.deleteUser(id);
		return ResponseEntity.ok("User deleted successfully");
	}

	@GetMapping
	public List<User> findAllUserDetails() {

		return userService.findAllUserDetails();
	}

	@GetMapping("/{id}")
	public Optional<User> findUserDetailsById(@PathVariable int id) {

		return userService.findUserDetailsById(id);
	}

	@GetMapping("/menu/{id}")
	public List<String> listOfMenu(@PathVariable int id) throws UserNotFoundException {

		return userService.listOfMenu(id);
	}

//	@GetMapping("/secure-data")
//	public ResponseEntity<?> getSecureData() {
//
//		String user = SecurityContextHolder.getContext().getAuthentication().getName();
//		return ResponseEntity.ok("Hello, " + user);
//	}
}
