package com.wipro.usermanagement.service;

import java.util.List;
import java.util.Optional;

import com.wipro.usermanagement.dto.UserDetails;
import com.wipro.usermanagement.entity.User;
import com.wipro.usermanagement.exception.DuplicateUserException;
import com.wipro.usermanagement.exception.InvalidCredentialsException;
import com.wipro.usermanagement.exception.UserNotFoundException;
import com.wipro.usermanagement.response.ApiResponse;

public interface UserService {

	ApiResponse userLogin(String email, String pass) throws InvalidCredentialsException;
	
	void addUser(UserDetails userDetails) throws DuplicateUserException;

	void updateUser(UserDetails userDetails) throws UserNotFoundException;

	void deleteUser(int id) throws UserNotFoundException;

	List<User> findAllUserDetails();

	Optional<User> findUserDetailsById(int id);

	List<String> listOfMenu(int id) throws UserNotFoundException;
}
