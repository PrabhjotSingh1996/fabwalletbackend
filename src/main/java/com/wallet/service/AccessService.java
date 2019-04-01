package com.wallet.service;

import com.wallet.model.Request;
import com.wallet.model.Response;

public interface AccessService {
	
	public Response signUp(Request request);
	
	public Response login(Request request);
	
	public Response logout(String msisdn);

}
