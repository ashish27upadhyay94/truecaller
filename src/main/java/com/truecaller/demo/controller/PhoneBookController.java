package com.truecaller.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.truecaller.demo.exception.handler.RecordNotFoundException;
import com.truecaller.demo.models.Users;
import com.truecaller.demo.service.PhoneBookService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class PhoneBookController {
	private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

	@Autowired
	private PhoneBookService phoneBookService;
	/**
	 * 
	 * @param username
	 * @return List<Users>
	 */
	@RequestMapping(value = "/byName/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Users> searchByUserName(@PathVariable final String username) {

		logger.info("Request Parameters {}", username);

		Optional<List<Users>> user = phoneBookService.searchUserByUserName(username);
		user.orElseThrow(() -> new RecordNotFoundException("No Record Found For This Particular User"));
		return user.get();
	}
	/**
	 * 
	 * @param number
	 * @return Users
	 */
	@RequestMapping(value = "/byNumber/{number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find User By PhoneNumber")
	public Users searchByPhoneNumber(@ApiParam(value = "Phone Number Of User",required = true) @PathVariable final Long number) {

		logger.info("Request Parameters {}", number);

		Optional<Users> user = phoneBookService.searchUserByPhoneNumber(number);
		user.orElseThrow(() -> new RecordNotFoundException("No Record Found For This Particular UserNumber"));
		return user.get();
	}
}
