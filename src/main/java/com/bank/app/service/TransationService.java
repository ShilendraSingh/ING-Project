package com.bank.app.service;

import java.util.List;

import com.bank.app.dto.TransationHistoryDto;
import com.bank.app.dto.TransationResponseDto;
import com.bank.app.dto.TransationDto;

public interface TransationService {

	public TransationResponseDto fundtransfer(Integer accountId, TransationDto transationdto);

	public List<TransationHistoryDto> viewTrsnasctions(Integer accountNumber, Integer weeks, Integer months);

}
