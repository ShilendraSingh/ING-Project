package com.bank.app.service;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bank.app.dto.CustomerDto;
import com.bank.app.dto.ResponseDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Customer;
import com.bank.app.exception.EmailException;
import com.bank.app.exception.PasswordMissMatchException;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.CustomerRepository;
import com.bank.app.utility.AccountType;
import com.bank.app.utility.BankUtility;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	Random random = new Random();

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public ResponseDto register(CustomerDto customerDto) {

		Customer customer = new Customer();
		Account account = new Account();
		ResponseDto customerResponsedto = new ResponseDto();

		if (customerDto.getPassword().equals(customerDto.getConfirmPassword())) {

			if (emailValidation(customerDto.getEmail())) {

				BeanUtils.copyProperties(customerDto, customer);
				Customer savedCustomer = customerRepository.save(customer);

				account.setAccountNumber(random.nextInt(1000000));
				account.setAccountType(AccountType.SAVING.name());
				account.setBalance(10000);

				account.setCustomerId(savedCustomer.getCustomerId());

				accountRepository.save(account);
				customerResponsedto.setMessgae(BankUtility.SUCCESS_MESSAGE);
				customerResponsedto.setStatusCode(HttpStatus.CREATED.value());

			} else {
				throw new EmailException(BankUtility.ENTER_VALID_EMAIL);
			}
		} else {
			throw new PasswordMissMatchException(BankUtility.PASSWORD_MISMATCH);
		}

		return customerResponsedto;
	}

	static boolean emailValidation(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
}