package com.wipro.usermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "USER_DATA")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "USER_F_NAME")
	private String firstName;

	@Column(name = "USER_L_NAME")
	private String lastName;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "PASS_WORD")
	private String password; // encrypted

	@Column(name = "ADDERESS")
	private String address;

	@Column(name = "USER_TYPE")
	private int userType; // 0-admin , 1- customer
}
