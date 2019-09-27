package com.bank.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

	public String message;
	public String status;
	public Integer customerId;
	public String customerName;
}
