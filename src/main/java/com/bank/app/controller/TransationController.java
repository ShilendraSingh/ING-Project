package com.bank.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.dto.TransationDto;
import com.bank.app.dto.TransationHistoryDto;
import com.bank.app.dto.TransationResponseDto;
import com.bank.app.service.TransationService;

@RestController
@RequestMapping("/api")
public class TransationController {

	@Autowired
	private TransationService transationService;

	@PostMapping("/customers/{accountId}/transations")
	public ResponseEntity<TransationResponseDto> fundtransfer(@PathVariable Integer accountId,@RequestBody TransationDto transationdto) {
		return new ResponseEntity<>(transationService.fundtransfer(accountId, transationdto), HttpStatus.CREATED);
	}

	@GetMapping("/customers/transations/{accountNumber}")
	public ResponseEntity<List<TransationHistoryDto>> viewTrsnasctions(@PathVariable int accountNumber,
			@RequestParam(required = false) Integer weeks, @RequestParam(required = false) Integer months) {
		return new ResponseEntity<>(transationService.viewTrsnasctions(accountNumber, weeks, months), HttpStatus.OK);
	}

}
