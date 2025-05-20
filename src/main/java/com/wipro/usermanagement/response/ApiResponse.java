package com.wipro.usermanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {

	Object data;
	String message;
}
