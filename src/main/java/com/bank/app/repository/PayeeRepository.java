package com.bank.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.entity.Payee;

public interface PayeeRepository extends JpaRepository<Payee, Integer> {

	public Optional<Payee> findByPayeeAccountNumber(int Account);
	
	public Optional<List<Payee>> findByAccountId(Integer accountId);

	public Optional<Payee> findByPayeeId(Integer payeeId);

}
