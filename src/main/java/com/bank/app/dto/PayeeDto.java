package com.bank.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PayeeDto {

	private Integer payeeId;
	private String payeeName;
	private Integer payeeAccountNumber;
	private String ifscCode;
	private String payeeEmailId;

}