package com.bank.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bank.app.dto.PayeeDto;
import com.bank.app.dto.ResponseDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Payee;
import com.bank.app.exception.CustomException;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.PayeeRepository;
import com.bank.app.utility.BankUtility;

@Service
public class PayeeServiceImpl implements PayeeService {

	@Autowired
	private PayeeRepository payeeRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public ResponseDto addPayee(Integer accountId, PayeeDto payeeDto) {
		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		if(!optionalAccount.isPresent()) {
			throw new CustomException(BankUtility.ACCOUNT_NOT_EXIST);
		}
		
		Optional<Payee> optionalPayee = payeeRepository.findByPayeeAccountNumber(payeeDto.getPayeeAccountNumber());
		if (optionalPayee.isPresent()) {
			throw new CustomException(BankUtility.PAYEE_EXIST);
		}

		Payee payee = new Payee();
		ResponseDto customerResponsedto = new ResponseDto();
		BeanUtils.copyProperties(payeeDto, payee);
		payee.setAccountId(optionalAccount.get().getAccountId());
		payeeRepository.save(payee);

		customerResponsedto.setMessgae(BankUtility.PAYEE_ADDED_MESSAGE);
		customerResponsedto.setStatusCode(HttpStatus.CREATED.value());
		return customerResponsedto;
	}

	
	@Override
	public List<PayeeDto> getAllPayee(Integer accountId) {
		
		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		if(!optionalAccount.isPresent()) {
			throw new CustomException(BankUtility.ACCOUNT_NOT_EXIST);
		}
		
		Optional<List<Payee>> optionalPayees = payeeRepository.findByAccountId(accountId);
		if(!optionalPayees.isPresent()) {
			throw new CustomException(BankUtility.PAYEE_NOT_EXIST);
		}
		List<PayeeDto> payeesDto = new ArrayList<>(); 
		optionalPayees.get().stream().forEach(payee -> {
			PayeeDto payeeDto = new PayeeDto();
			BeanUtils.copyProperties(payee, payeeDto);
			payeesDto.add(payeeDto);
		});
		return payeesDto;
	}

	@Override
	public ResponseDto updatePayee(Integer accountId, PayeeDto payeeDto) {
		
		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		if(!optionalAccount.isPresent()) {
			throw new CustomException(BankUtility.ACCOUNT_NOT_EXIST);
		}
		
		Optional<Payee> optionalPayee = payeeRepository.findByPayeeId(payeeDto.getPayeeId());
		ResponseDto customerResponsedto = new ResponseDto();
		if (optionalPayee.isPresent()) {
			optionalPayee.get().setIfscCode(payeeDto.getIfscCode());
			optionalPayee.get().setPayeeAccountNumber(payeeDto.getPayeeAccountNumber());
			optionalPayee.get().setPayeeEmailId(payeeDto.getPayeeEmailId());
			optionalPayee.get().setPayeeName(payeeDto.getPayeeName());
			payeeRepository.save(optionalPayee.get());
			
			customerResponsedto.setMessgae(BankUtility.PAYEE_UPDATED_MESSAGE);
			customerResponsedto.setStatusCode(HttpStatus.OK.value());
		} else {
			throw new CustomException(BankUtility.PAYEE_NOT_EXIST);
		}
		return customerResponsedto;
	}
	
	@Override
	public ResponseDto deletePayee(Integer accountId, Integer payeeId) {

		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		if(!optionalAccount.isPresent()) {
			throw new CustomException(BankUtility.ACCOUNT_NOT_EXIST);
		}
		
		ResponseDto customerResponsedto = new ResponseDto();
		Optional<Payee> payeeid = payeeRepository.findById(payeeId);
		if (payeeid.isPresent()) {
			payeeRepository.deleteById(payeeId);
		} else {
			throw new CustomException(BankUtility.PAYEE_NOT_EXIST);
		}
		
		customerResponsedto.setMessgae(BankUtility.PAYEE_DELETED_MESSAGE);
		customerResponsedto.setStatusCode(HttpStatus.OK.value());
		
		return customerResponsedto;
	}
	
}
