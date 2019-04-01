package com.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.model.Request;
import com.wallet.model.Response;
import com.wallet.service.AccessService;

@RestController
@RequestMapping("/fab")
public class AccessController {
	
	@Autowired
	private AccessService accessService;
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public Response signup(@RequestBody Request request) {
		return accessService.signUp(request);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Response login(@RequestBody Request request) {
		return accessService.login(request);
	}
	
	@RequestMapping(value="/logout/{msisdn}",method=RequestMethod.GET)
	public Response logout(@PathVariable("msisdn") String msisdn) {
		return accessService.logout(msisdn);
	}

}
