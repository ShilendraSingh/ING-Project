package com.bank.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bank.app.dto.TransationDto;
import com.bank.app.dto.TransationHistoryDto;
import com.bank.app.dto.TransationResponseDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Payee;
import com.bank.app.entity.Transation;
import com.bank.app.exception.CustomException;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.PayeeRepository;
import com.bank.app.repository.TransationRepository;
import com.bank.app.utility.BankUtility;

//1.INPUT FOR OTHER ACCOUNT.1.ACCOUNT NUMBER 2.FROM ACCOUNT 3.AMOUNT
//2.CHECK IF IT IS UR OWN ACCOUNT.ACCOUNTREPO HIT RESONSE ME CUSTID ==UR CUST ID EXP
//ACCOUNT NUMBER JPA METHOD
//3.EVERYTHING IS FINE THEN CHECKFOR -VE AMOUNT
//4.AMOUNT >= UR ACOOUNT BAL
//5.DEDEUCT AMOUT FROM UR ACCOUNT AND ADD IN TO ACCOUNT
//6.INSERT A RECORD IN TRANSACTION TABLE TO AACC FROM ACC AMOUNT

@Service
public class TransationServiceImpl implements TransationService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransationRepository transationRepository;
	
	@Autowired
	private PayeeRepository payeeRepository;

// reading from properties file

	@Value("${no.of.days}")
	private int noOfDays;

	@Override
	public TransationResponseDto fundtransfer(Integer accountId, TransationDto transationdto) {

		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		Optional<Payee> optionalPayee = payeeRepository.findById(transationdto.getPayeeId());
		if (!optionalAccount.isPresent()) {
			throw new CustomException(BankUtility.ACCOUNT_NOT_EXIST);
		}

		if (!optionalPayee.isPresent()) {
			throw new CustomException(BankUtility.PAYEE_NOT_EXIST);
		}
		
		if(transationdto.getAmount()<0) {
			throw new CustomException(BankUtility.INVALID_AMOUNT);
		}
		
		if (!(optionalAccount.get().getBalance() > transationdto.getAmount())) {
			throw new CustomException(BankUtility.INSUFFICENT_AMOUNT);
		}

		TransationResponseDto transationResponseDto = new TransationResponseDto();
		Transation transation = new Transation();
		transation.setAmount(transationdto.getAmount());
		transation.setFromAccount(optionalAccount.get().getAccountNumber());
		transation.setToAccount(optionalPayee.get().getPayeeAccountNumber());
		transation.setTransactionDate(LocalDateTime.now());
		transation.setTransactionType("DEBIT");
		transation = transationRepository.save(transation);
		
		Double balanceAmount = optionalAccount.get().getBalance()-transationdto.getAmount();
		optionalAccount.get().setBalance(balanceAmount);
		accountRepository.save(optionalAccount.get());
		
		transationResponseDto.setMessage(BankUtility.TRANSACTION_SUCCESS);
		transationResponseDto.setTransactionId(transation.getTransationId());
		return transationResponseDto;
	}

/*	@Override
	public List<TransationHistoryDto> viewHistory(int accountNumber) {

		List<TransationHistoryDto> responseList = new ArrayList<>();
		Pageable pageable = PageRequest.of(0, 10);
		List<Transation> history = transationRepository.findByFromAccount(accountNumber, pageable);
		for (Transation transation : history) {

			TransationHistoryDto transationHistorydto = new TransationHistoryDto();

			BeanUtils.copyProperties(transation, transationHistorydto);
			transationHistorydto.setTransationId(transation.getTransationId());

			responseList.add(transationHistorydto);

		}

		return responseList;
	}
*/
	@Override
	public List<TransationHistoryDto> viewTrsnasctions(Integer accountNumber, Integer weeks, Integer months) {

		List<TransationHistoryDto> transationsHistorydto = new ArrayList<>();
		Pageable pageable = PageRequest.of(0, 10);
		List<Transation> transactions = null;

		// minus day/date = back day/date

		LocalDate fromDate = LocalDate.now();
		LocalDate currentDate = LocalDate.now();
		if(weeks == null && months == null) {
			transactions = transationRepository.findByFromAccount(accountNumber, pageable);
		} else {
			if(weeks != null) {
				fromDate = fromDate.minusWeeks(weeks);
			} else {
				fromDate = fromDate.minusMonths(months);
			}
			transactions = transationRepository.findBetweenDays(fromDate, currentDate, accountNumber,pageable);
		}
		
		transactions.stream().forEach(transaction -> {
			TransationHistoryDto transationHistorydto = new TransationHistoryDto();
			BeanUtils.copyProperties(transaction, transationHistorydto);
			transationsHistorydto.add(transationHistorydto);
		});

		return transationsHistorydto;
	}

}
