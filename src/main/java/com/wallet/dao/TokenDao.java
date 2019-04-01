package com.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.entity.Token;

public interface TokenDao extends JpaRepository<Token, String> {
	
	public Token findByMsisdn(String msisdn);
	
	public Token findByMsisdnAndIsActive(String msisdn, boolean isActive);

	
}
