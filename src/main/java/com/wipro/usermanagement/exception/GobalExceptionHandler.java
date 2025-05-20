package com.wipro.usermanagement.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wipro.usermanagement.entity.User;
import com.wipro.usermanagement.response.ApiResponse;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class GobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<User> userNotFoundExceptionHandler(Exception ex) {
		System.out.println(ex.getMessage());
		HttpHeaders header = new HttpHeaders();
		header.add("app-version", "1.0");
		header.add("error message", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(null);
	}

	@ExceptionHandler(DuplicateUserException.class)
	ResponseEntity<ApiResponse> duplicateUserException(Exception ex) {
		System.out.println(ex.getMessage());
		HttpHeaders header = new HttpHeaders();
		header.add("app-version", "1.0");
		header.add("error message", ex.getMessage());
		ApiResponse apiResponse = new ApiResponse(null, "Duplicate email id present in the request");

		return ResponseEntity
				.status(HttpStatus.OK)
				.headers(header)
				.body(apiResponse);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	ResponseEntity<ApiResponse> invalidCredentialsException(Exception ex) {

		System.out.println(ex.getMessage());
		HttpHeaders header = new HttpHeaders();
		header.add("app-version", "1.0");
		header.add("error message", ex.getMessage());
		ApiResponse apiResponse = new ApiResponse(null, "Incorrect Email Id or Password");
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.headers(header)
				.body(apiResponse);
	}
}
