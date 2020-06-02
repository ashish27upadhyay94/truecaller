package com.truecaller.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.truecaller.demo.models.JwtAuthenticationRequest;
import com.truecaller.demo.models.JwtAuthenticationResponse;
import com.truecaller.demo.util.JwtUtility;

@RestController
public class AuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailService;

	@Autowired
	private JwtUtility jwtutil;

	/**
	 * This Method Created JWT Authentication Token.
	 * 
	 * @param authenticationRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
			throws Exception {

		logger.info("Request Parameters {}", authenticationRequest.toString());

		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
		UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUserName());
		String token = jwtutil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));

	}

	/**
	 * This Method Authenticates UserName And Password.
	 * 
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			logger.info("Entering authenticate() Method");

			/*
			 * This Call Authenticates UserName And Password Provided By The User To The
			 * User Information Stored in MyUserDetailService.
			 */
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			logger.info("Exiting authenticate() Method");

		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
