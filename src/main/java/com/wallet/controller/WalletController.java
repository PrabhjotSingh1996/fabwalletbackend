package com.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.model.Request;
import com.wallet.model.Response;
import com.wallet.service.WalletService;

@RestController
@RequestMapping("/fab")
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	@RequestMapping(value = "/addmoney", method=RequestMethod.POST)
	public Response addMoney(@RequestBody Request request) {
		return walletService.addMoney(request);
	}
	
	@RequestMapping(value = "/getmoney", method=RequestMethod.POST)
	public Response getMoney(@RequestBody Request request) {
		return walletService.getMoney(request);
	}

}
