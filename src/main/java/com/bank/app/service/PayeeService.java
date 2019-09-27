package com.bank.app.service;

import java.util.List;

import com.bank.app.dto.PayeeDto;
import com.bank.app.dto.ResponseDto;

public interface PayeeService {

	public ResponseDto addPayee(Integer accountId, PayeeDto payeeRequestdto);

	public List<PayeeDto> getAllPayee(Integer accountId);

	public ResponseDto updatePayee(Integer accountId,PayeeDto payeeDto);

	public ResponseDto deletePayee(Integer accountId, Integer payeeId);

}
