package com.bank.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.app.dto.LoginDto;
import com.bank.app.dto.LoginResponseDto;
import com.bank.app.entity.Customer;
import com.bank.app.exception.CustomException;
import com.bank.app.repository.CustomerRepository;
import com.bank.app.utility.BankUtility;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public LoginResponseDto login(LoginDto logindto) {

		LoginResponseDto loginResponseDto = new LoginResponseDto();

		Customer customer = customerRepository.findByEmailAndPassword(logindto.getEmailId(), logindto.getPassword());

		if (customer == null) {
			throw new CustomException(BankUtility.INVALID_CUSTOMER);
		}

		loginResponseDto.setMessage(BankUtility.SUCCESS_MESSAGE);
		loginResponseDto.setStatus(BankUtility.STATUS_SUCCCESS);
		loginResponseDto.setCustomerId(customer.getCustomerId());
		loginResponseDto.setCustomerName(customer.getName());

		return loginResponseDto;
	}
}
