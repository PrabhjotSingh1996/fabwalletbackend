package com.wallet.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.commons.Constants;
import com.wallet.commons.ValidateUtils;
import com.wallet.dao.TokenDao;
import com.wallet.dao.UsersDao;
import com.wallet.dao.WalletDao;
import com.wallet.entity.Token;
import com.wallet.entity.Users;
import com.wallet.entity.Wallet;
import com.wallet.model.ApplicationException;
import com.wallet.model.Error;
import com.wallet.model.Request;
import com.wallet.model.Response;
import com.wallet.service.AccessService;

@Service
public class AccessServiceImpl implements AccessService {

	@Autowired
	private ValidateUtils validateUtils;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private TokenDao tokenDao;
	
	@Autowired
	private WalletDao walletDao;

	@Override
	public Response signUp(Request request) {
		Response response = new Response();
		try {
			validateUtils.validateForSignUp(request);
			Users users = usersDao.findByMsisdn(request.getMsisdn());
			if (null != users)
				throw new ApplicationException(Constants.USER_ALREADY_EXISTS, "MSISDN already exists");
			users = new Users();
			users.setMsisdn(request.getMsisdn());
			users.setPassword(request.getPassword());
			users.setUserName(request.getUserName());
			usersDao.save(users);
			Wallet wallet = new Wallet();
			wallet.setMsisdn(request.getMsisdn());
			wallet.setBalance(0);
			walletDao.save(wallet);
			response.setStatus(Constants.SUCCESS_STATUS);
			response.setMessage("Welcome to fab wallet.Please login to get access token");
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
	public Response login(Request request) {
		Response response = new Response();
		try {
			validateUtils.validateForLogin(request);
			Users users = usersDao.findByMsisdn(request.getMsisdn());
			if (null == users)
				throw new ApplicationException(Constants.INVALID_MSISDN, "Invalid Msisdn");
			if (!request.getPassword().equals(users.getPassword()))
				throw new ApplicationException(Constants.INVALID_PASSWORD, "Invalid Password");
			Token token = tokenDao.findByMsisdn(request.getMsisdn());
			if (null == token) {
				token = new Token();
				token.setMsisdn(users.getMsisdn());
			}
			token.setAccessToken(UUID.randomUUID().toString());
			token.setActive(true);
			tokenDao.save(token);
			response.setStatus(Constants.SUCCESS_STATUS);
			response.setMessage("Login Successfull");
			response.setAccessToken(token.getAccessToken());
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
	public Response logout(String msisdn) {
		Response response = new Response();
		try {
			response.setStatus(Constants.SUCCESS_STATUS);
			response.setMessage("Logout Successfull");
			Token token = tokenDao.findByMsisdn(msisdn);
			if (null == token) 
				return response;
			token.setAccessToken(null);
			token.setActive(false);
			tokenDao.save(token);
		} catch (Exception exception) {
			response.setStatus(Constants.FAIL_STATUS);
			response.setError(new Error(Constants.PROBLEM_IN_APPLICATION, exception.getMessage()));
		}
		return response;
	}

}
