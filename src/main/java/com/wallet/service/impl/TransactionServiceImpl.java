package com.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.commons.Constants;
import com.wallet.commons.ValidateUtils;
import com.wallet.dao.TransactionDao;
import com.wallet.model.ApplicationException;
import com.wallet.model.Request;
import com.wallet.model.Response;
import com.wallet.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private ValidateUtils validateUtils;

	@Autowired
	private TransactionDao transactionDao;

	@Override
	public Response getTransactions(Request request) {
		Response response = new Response();
		try {
			validateUtils.validateAccess(request);
			response.setTransactions(transactionDao.findByMsisdn(request.getMsisdn()));
			response.setStatus(Constants.SUCCESS_STATUS);
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
