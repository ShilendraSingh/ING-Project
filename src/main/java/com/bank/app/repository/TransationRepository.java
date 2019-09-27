package com.bank.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.app.entity.Transation;

@Repository
// @Projection(name = "transationRepository", types = Transation.class)
public interface TransationRepository extends JpaRepository<Transation, Integer> {

	public List<Transation> findByFromAccount(int accountNumber, Pageable pageable);

	@Query(value = "SELECT txn FROM Transation txn WHERE txn.transactionDate BETWEEN :fromDate AND :currentDate AND txn.fromAccount= :accountNumber")
	public List<Transation> findBetweenDays(LocalDate fromDate, LocalDate currentDate, Integer accountNumber, Pageable pageable);
}
