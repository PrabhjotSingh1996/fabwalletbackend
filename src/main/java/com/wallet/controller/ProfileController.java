package com.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.model.Request;
import com.wallet.model.Response;
import com.wallet.service.ProfileService;

@RestController
@RequestMapping("/fab")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value="/profile",method=RequestMethod.POST)
	public Response viewProfile(@RequestBody Request request) {
		return profileService.viewProfile(request);
	}
	
	@RequestMapping(value="/profile",method=RequestMethod.PUT)
	public Response updateProfile(@RequestBody Request request) {
		return profileService.updateProfile(request);
	}
	
}
