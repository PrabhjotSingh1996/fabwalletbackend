package com.wallet.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.commons.Constants;
import com.wallet.commons.ValidateUtils;
import com.wallet.dao.TransactionDao;
import com.wallet.dao.WalletDao;
import com.wallet.entity.Transaction;
import com.wallet.entity.Wallet;
import com.wallet.model.ApplicationException;
import com.wallet.model.Request;
import com.wallet.model.Response;
import com.wallet.service.WalletService;

@Service
public class WalletSeviceImpl implements WalletService {
	
	@Autowired
	private ValidateUtils validateUtils;
	
	@Autowired
	private TransactionDao transactionDao;;
	
	@Autowired
	private WalletDao walletDao;

	@Override
	public Response addMoney(Request request) {
		Response response = new Response();
		try {
			validateUtils.validateAccess(request);
			Wallet wallet = walletDao.findByMsisdn(request.getMsisdn());
			int previousBalance = wallet.getBalance();
			int postBalance = previousBalance + request.getAmount();
			wallet.setBalance(postBalance);
			walletDao.save(wallet);
			
			Transaction transaction = new Transaction();
			transaction.setAmount(request.getAmount());
			transaction.setMsisdn(request.getMsisdn());
			transaction.setPostBalance(postBalance);
			transaction.setPreviousBalance(previousBalance);
			transaction.setTransactionDate(LocalDateTime.now());
			transaction.setTransactionType(Constants.ADD_MONEY);
			transactionDao.save(transaction);
			
			response.setStatus(Constants.SUCCESS_STATUS);
			response.setMessage("Add money successfull. New balance of " + request.getMsisdn() + " is " + postBalance);
		} catch (ApplicationException ae) {
			response.setStatus(ae.getStatus());
			response.setMessage(ae.getMessage());
		} catch (Exception exception) {
			response.setStatus(Constants.PROBLEM_IN_APPLICATION);
			response.setMessage(exception.getMessage());
		}
		return response;
	}

	@Override
	public Response getMoney(Request request) {
		Response response = new Response();
		try {
			validateUtils.validateAccess(request);
			Wallet wallet = walletDao.findByMsisdn(request.getMsisdn());
			int previousBalance = wallet.getBalance();
			int postBalance = previousBalance - request.getAmount();
			if(postBalance<0)
				throw new ApplicationException(Constants.INSUFFICENT_BALANCE, "Insufficent Balance");
			wallet.setBalance(postBalance);
			walletDao.save(wallet);
			
			Transaction transaction = new Transaction();
			transaction.setAmount(request.getAmount());
			transaction.setMsisdn(request.getMsisdn());
			transaction.setPostBalance(postBalance);
			transaction.setPreviousBalance(previousBalance);
			transaction.setTransactionDate(LocalDateTime.now());
			transaction.setTransactionType(Constants.GET_MONEY);
			transactionDao.save(transaction);
			
			response.setStatus(Constants.SUCCESS_STATUS);
			response.setMessage("Withdrawal. New balance of " + request.getMsisdn() + " is " + postBalance);
		} catch (ApplicationException ae) {
			response.setStatus(ae.getStatus());
			response.setMessage(ae.getMessage());
		} catch (Exception exception) {
			response.setStatus(Constants.PROBLEM_IN_APPLICATION);
			response.setMessage(exception.getMessage());
		}
		return response;
	}

}
