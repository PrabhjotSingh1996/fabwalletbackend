package com.wallet.service;

import com.wallet.model.Request;
import com.wallet.model.Response;

public interface ProfileService {
	
	public Response viewProfile(Request request);
	
	public Response updateProfile(Request request);

}
