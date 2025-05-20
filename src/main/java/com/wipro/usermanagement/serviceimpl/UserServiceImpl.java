package com.wipro.usermanagement.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.usermanagement.component.JwtUtil;
import com.wipro.usermanagement.constants.AppConstants;
import com.wipro.usermanagement.dto.LoginResponse;
import com.wipro.usermanagement.dto.UserDetails;
import com.wipro.usermanagement.entity.User;
import com.wipro.usermanagement.exception.DuplicateUserException;
import com.wipro.usermanagement.exception.InvalidCredentialsException;
import com.wipro.usermanagement.exception.UserNotFoundException;
import com.wipro.usermanagement.repository.UserRepository;
import com.wipro.usermanagement.response.ApiResponse;
import com.wipro.usermanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	public void addUser(UserDetails userDetails) throws DuplicateUserException {
		Optional<User> u = repository.findByEmailId(userDetails.getEmailId());
		if (u.isEmpty()) {
			User user = new User();
			user.setFirstName(userDetails.getFirstName());
			user.setLastName(userDetails.getLastName());
			user.setAddress(userDetails.getAddress());
			user.setEmailId(userDetails.getEmailId());
			user.setPassword(userDetails.getPassword());
			user.setUserId(userDetails.getUserId());
			user.setUserType(1);
			repository.save(user);
		} else {
			throw new DuplicateUserException("Email " + userDetails.getEmailId() + " already exists");
		}
	}

	@Override
	public void updateUser(UserDetails userDetails) throws UserNotFoundException {

		Optional<User> u = repository.findByEmailId(userDetails.getEmailId());
		if (u.isPresent()) {
			User user = u.get();
			if (userDetails.getFirstName() != null && !userDetails.getFirstName().isEmpty()) {
				user.setFirstName(userDetails.getFirstName());
			}
			if (userDetails.getLastName() != null && !userDetails.getLastName().isEmpty()) {
				user.setLastName(userDetails.getLastName());
			}
			if (userDetails.getAddress() != null && !userDetails.getAddress().isEmpty()) {
				user.setAddress(userDetails.getAddress());
			}
			if (userDetails.getEmailId() != null && !userDetails.getEmailId().isEmpty()) {
				user.setEmailId(userDetails.getEmailId());
			}
			if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
				user.setPassword(userDetails.getPassword());
			}
			if (userDetails.getUserId() != null && !userDetails.getUserId().isEmpty()) {
				user.setUserId(userDetails.getUserId());
			}
			repository.save(user);
		} else {
			throw new UserNotFoundException("User with email id " + userDetails.getEmailId() + " not found");
		}
	}

	@Override
	public void deleteUser(int id) throws UserNotFoundException {

		Optional<User> u = repository.findById(id);
		if (u.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new UserNotFoundException("User with id " + id + " not found");
		}
	}

	@Override
	public List<User> findAllUserDetails() {

		return repository.findAll();
	}

	@Override
	public Optional<User> findUserDetailsById(int id) {

		return repository.findById(id);
	}

	@Override
	public List<String> listOfMenu(int id) throws UserNotFoundException {

		List<String> listOfMenu = new ArrayList<>();
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			if (user.get().getUserType() == 0) {
				listOfMenu.add("DashBoard");
				listOfMenu.add("Manage Products");
				listOfMenu.add("Manage Users");
				listOfMenu.add("View Orders");
			} else {
				listOfMenu.add("Profile");
				listOfMenu.add("Home");
				listOfMenu.add("Shop");
				listOfMenu.add("My Cart");
				listOfMenu.add("My Orders");
			}
		} else {
			throw new UserNotFoundException("User with id " + id + " not found");
		}
		return listOfMenu;
	}

	@Override
	public ApiResponse userLogin(String email, String password) throws InvalidCredentialsException {

		ApiResponse apiResponse = null;
		Optional<User> userEntity = repository.findByEmailId(email);

		LoginResponse loginResponse = new LoginResponse();

		if (userEntity.isPresent()) {
			User userDetails = userEntity.get();

			if (userDetails.getPassword().equals(password)) {
				String token = jwtUtil.generateToken(email);
				loginResponse.setId(userDetails.getId());
				loginResponse.setFullName(userDetails.getFirstName() + " " + userDetails.getLastName());
				loginResponse.setEmailId(userDetails.getEmailId());
				loginResponse.setUserId(userDetails.getUserId());
				loginResponse.setRoleId(userDetails.getUserType());
				loginResponse.setToken("Bearer " + token);
				apiResponse = new ApiResponse(loginResponse, AppConstants.LOGIN_SUCCESS);
				return apiResponse;
			} else {
				throw new InvalidCredentialsException("Incorrect username or password");
			}
		} else {
			throw new InvalidCredentialsException(
					"Email Id not present in the data base or password is null. Please try to register.");
		}
	}
}
