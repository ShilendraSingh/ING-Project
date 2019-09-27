package com.bank.app.service;

import com.bank.app.dto.LoginDto;
import com.bank.app.dto.LoginResponseDto;

public interface LoginService {

	public LoginResponseDto login(LoginDto customerLogindto);

}
