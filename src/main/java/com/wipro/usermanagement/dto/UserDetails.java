package com.wipro.usermanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class UserDetails {

	private String firstName;
	private String lastName;
	private String emailId;
	private String userId;
	private String password;
	private String address;
}
