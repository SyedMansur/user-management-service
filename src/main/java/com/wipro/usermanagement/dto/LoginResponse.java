package com.wipro.usermanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class LoginResponse {

	private int id;
	private String fullName;
	private String emailId;
	private String userId;
	private int roleId;
	private String token;
}
