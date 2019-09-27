package com.bank.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bank.app.utility.BankUtility;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> customException(CustomException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), BankUtility.STATUS_FAIL);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AmountInsuffcientException.class)
	public ResponseEntity<ErrorResponse> amountInsuffcientException(AmountInsuffcientException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), BankUtility.STATUS_FAIL);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PasswordMissMatchException.class)
	public ResponseEntity<ErrorResponse> passwordMissMatchException(PasswordMissMatchException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), BankUtility.STATUS_FAIL);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
