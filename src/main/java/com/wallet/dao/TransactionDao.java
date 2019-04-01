package com.wallet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.entity.Transaction;

public interface TransactionDao extends JpaRepository<Transaction, Long> {

	public List<Transaction> findByMsisdn(String msisdn);
	
}
