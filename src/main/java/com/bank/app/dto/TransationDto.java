package com.bank.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransationDto {
	private Integer payeeId;
	private double amount;
}
