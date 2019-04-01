package com.wallet.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	
	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	
	@Column(name="msisdn",nullable=false)
	private String msisdn;
	
	@Column(name="amount",nullable=false)
	private int amount;
	
	@Column(name="transaction_type",nullable=false)
	private String transactionType;
	
	@Column(name="previous_balance",nullable=false)
	private int previousBalance;
	
	@Column(name="post_balance",nullable=false)
	private int postBalance;
	
	@Column(name="transaction_date",nullable=false)
	private String transactionDate;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = DateTimeFormatter.ofPattern("dd/MM/YYYY hh:mm:ss", Locale.ENGLISH).format(transactionDate);;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(int previousBalance) {
		this.previousBalance = previousBalance;
	}

	public int getPostBalance() {
		return postBalance;
	}

	public void setPostBalance(int postBalance) {
		this.postBalance = postBalance;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
