package com.wallet.service;

import com.wallet.model.Request;
import com.wallet.model.Response;

public interface TransactionService {
	
	public Response getTransactions(Request request);

}
