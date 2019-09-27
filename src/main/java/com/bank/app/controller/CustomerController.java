package com.bank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.dto.CustomerDto;
import com.bank.app.dto.LoginDto;
import com.bank.app.dto.LoginResponseDto;
import com.bank.app.dto.ResponseDto;
import com.bank.app.service.LoginService;
import com.bank.app.service.RegistrationService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private LoginService loginService;

	@PostMapping("/customers")
	public ResponseEntity<ResponseDto> register(@RequestBody CustomerDto customerRequestdto) {
		return new ResponseEntity<>(registrationService.register(customerRequestdto), HttpStatus.CREATED);
	}

	@PostMapping("/customers/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto customerLogindto) {
		return new ResponseEntity<>(loginService.login(customerLogindto), HttpStatus.ACCEPTED);
	}

}
