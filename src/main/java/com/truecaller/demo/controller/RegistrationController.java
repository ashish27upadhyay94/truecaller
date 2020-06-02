package com.truecaller.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.truecaller.demo.models.Users;
import com.truecaller.demo.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class RegistrationController {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	UserService userService;

	/**
	 * <h1>registerUser</h1> This Method Is Use To Register The User
	 * 
	 * @param user
	 * @return users
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Register User",notes = "Register User Via Phone Number,Each User Can Register With Only One Unique Phone Number")
	public Users registerUser(@Valid @RequestBody Users user) {
		logger.info("Request Parameters {}", user.toString());
		return userService.saveUser(user);

	}
}
