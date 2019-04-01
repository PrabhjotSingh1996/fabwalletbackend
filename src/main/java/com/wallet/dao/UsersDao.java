package com.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallet.entity.Users;

@Repository
public interface UsersDao extends JpaRepository<Users, String> {
	
	public Users findByMsisdn(String msisdn);

}
