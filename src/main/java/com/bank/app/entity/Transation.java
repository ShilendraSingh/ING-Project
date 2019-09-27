package com.bank.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Transation implements Serializable{
	

	private static final long serialVersionUID = 3963411852888387174L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transationId;
	private Integer toAccount;
	private Integer fromAccount;
	private Double amount;
	private LocalDateTime transactionDate;
	private String transactionType;

}
