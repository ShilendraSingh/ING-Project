package com.bank.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Payee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer payeeId;
	private String payeeName;
	private Integer payeeAccountNumber;
	private String ifscCode;
	private String payeeEmailId;
	private Integer accountId;
}
