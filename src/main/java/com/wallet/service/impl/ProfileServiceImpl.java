package com.wallet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wallet.commons.Constants;
import com.wallet.commons.ValidateUtils;
import com.wallet.dao.TransactionDao;
import com.wallet.dao.UsersDao;
import com.wallet.dao.WalletDao;
import com.wallet.entity.Transaction;
import com.wallet.entity.Users;
import com.wallet.entity.Wallet;
import com.wallet.model.ApplicationException;
import com.wallet.model.Error;
import com.wallet.model.Request;
import com.wallet.model.Response;
import com.wallet.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ValidateUtils validateUtils;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private TransactionDao transactionDao;

	@Override
	public Response viewProfile(Request request) {
		Response response = new Response();
		try {
			validateUtils.validateAccess(request);
			Users users = usersDao.findByMsisdn(request.getMsisdn());
			Wallet wallet = walletDao.findByMsisdn(request.getMsisdn());
			List<Transaction> transactions = transactionDao.findByMsisdn(request.getMsisdn());
			if (null == users)
				throw new ApplicationException(Constants.USER_NOT_FOUND, "User not found");
			if (null == wallet)
				throw new ApplicationException(Constants.WALLET_NOT_FOUND, "Wallet not found");
			response.setStatus(Constants.SUCCESS_STATUS);
			response.setUsers(users);
			response.setWallet(wallet);
			response.setTransactions(transactions);
		} catch (ApplicationException ae) {
			response.setStatus(Constants.FAIL_STATUS);
			response.setError(new Error(ae.getStatus(), ae.getMessage()));
		} catch (Exception exception) {
			response.setStatus(Constants.FAIL_STATUS);
			response.setError(new Error(Constants.PROBLEM_IN_APPLICATION, exception.getMessage()));
		}
		return response;
	}

	@Override
	public Response updateProfile(Request request) {
		Response response = new Response();
		try {
			validateUtils.validateAccess(request);
			Users users = usersDao.findByMsisdn(request.getMsisdn());
			if(!StringUtils.isEmpty(request.getPassword()))
				users.setPassword(request.getPassword());
			if(!StringUtils.isEmpty(request.getUserName()))
				users.setUserName(request.getUserName());
			usersDao.save(users);
			response.setStatus(Constants.SUCCESS_STATUS);
			response.setMessage("Profile Updated Successfully");
		} catch (ApplicationException ae) {
			response.setStatus(Constants.FAIL_STATUS);
			response.setError(new Error(ae.getStatus(), ae.getMessage()));
		} catch (Exception exception) {
			response.setStatus(Constants.FAIL_STATUS);
			response.setError(new Error(Constants.PROBLEM_IN_APPLICATION, exception.getMessage()));
		}
		return response;
	}

}
