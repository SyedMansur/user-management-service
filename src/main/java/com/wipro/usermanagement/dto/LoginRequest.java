package com.wipro.usermanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class LoginRequest {

	String emailId;
	String password;
}
