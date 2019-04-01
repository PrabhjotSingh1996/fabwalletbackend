package com.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.model.Request;
import com.wallet.model.Response;
import com.wallet.service.TransactionService;

@RestController
@RequestMapping("/fab")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value="/passbook",method=RequestMethod.POST)
	public Response signup(@RequestBody Request request) {
		return transactionService.getTransactions(request);
	}

}
