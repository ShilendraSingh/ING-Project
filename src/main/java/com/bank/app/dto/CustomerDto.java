package com.bank.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private String name;
	private int age;
	private String mobileNumber;
	private String gender;
	private String password;
	private String confirmPassword;
	private String email;

}
