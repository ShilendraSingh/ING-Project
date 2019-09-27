package com.bank.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.dto.PayeeDto;
import com.bank.app.dto.ResponseDto;
import com.bank.app.service.PayeeService;

@RestController
@RequestMapping("/api")
public class PayeeController {

	@Autowired
	private PayeeService payeeService;

	@PostMapping("/customers/{accountId}/payees")
	public ResponseEntity<ResponseDto> addPayee(@PathVariable Integer accountId, @RequestBody PayeeDto payeeDto) {
		return new ResponseEntity<>(payeeService.addPayee(accountId, payeeDto), HttpStatus.CREATED);

	}

	@GetMapping("/customers/{accountId}/payees")
	public ResponseEntity<List<PayeeDto>> getAllPayee(@PathVariable Integer accountId) {
		return new ResponseEntity<>(payeeService.getAllPayee(accountId), HttpStatus.OK);
	}

	@PutMapping("/customers/{accountId}/payees")
	public ResponseEntity<ResponseDto> updatePayee(@PathVariable Integer accountId, @RequestBody PayeeDto payeeDto) {
		return new ResponseEntity<>(payeeService.updatePayee(accountId, payeeDto), HttpStatus.OK);

	}

	@DeleteMapping("/customers/{accountId}/payees/{payeeId}")
	public ResponseEntity<ResponseDto> deletePayee(@PathVariable Integer accountId, @PathVariable int payeeId) {
		return new ResponseEntity<>(payeeService.deletePayee(accountId, payeeId), HttpStatus.OK);

	}

}
