package com.wallet.service;

import com.wallet.model.Request;
import com.wallet.model.Response;

public interface WalletService {
	
	public Response addMoney(Request request);
	
	public Response getMoney(Request request);

}
