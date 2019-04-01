package com.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.entity.Wallet;

public interface WalletDao extends JpaRepository<Wallet, String> {
	
	public Wallet findByMsisdn(String msisdn);

}
