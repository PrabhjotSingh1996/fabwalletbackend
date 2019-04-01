package com.wallet.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.wallet.dao.TokenDao;
import com.wallet.entity.Token;
import com.wallet.model.ApplicationException;
import com.wallet.model.Request;


@Component
public class ValidateUtils {
	
	@Autowired
	private TokenDao tokenDao;
	
	public void validateForSignUp(Request request) throws ApplicationException {
		String message = null;
		if(StringUtils.isEmpty(request.getPassword()))
			message = "Password missing";
		if(StringUtils.isEmpty(request.getUserName()))
			message = "User Name missing";
		if(StringUtils.isEmpty(request.getMsisdn()))
			message = "Msisdn missing";
		if(message!=null)
			throw new ApplicationException(Constants.MANDATORY_FIELD_MISSING, message);
	}
	
	public void validateForLogin(Request request) throws ApplicationException {
		String message = null;
		if(StringUtils.isEmpty(request.getPassword()))
			message = "Password missing";
		if(StringUtils.isEmpty(request.getMsisdn()))
			message = "Msisdn missing";
		if(message!=null)
			throw new ApplicationException(Constants.MANDATORY_FIELD_MISSING, message);
	}
	
	public void validateAccess(Request request) throws ApplicationException {
		String message = null;
		if(StringUtils.isEmpty(request.getMsisdn()))
			message = "Msisdn missing";
		if(StringUtils.isEmpty(request.getAccessToken()))
			message = "Access Token missing";
		if(message!=null)
			throw new ApplicationException(Constants.MANDATORY_FIELD_MISSING, message);
		Token token = tokenDao.findByMsisdnAndIsActive(request.getMsisdn(),true);
		if(token==null || !request.getAccessToken().equals(token.getAccessToken()))
			throw new ApplicationException(Constants.ACCESS_DENIED, "Acess Denied!");
	}

}
