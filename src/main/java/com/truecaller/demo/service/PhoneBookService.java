package com.truecaller.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.truecaller.demo.dao.PhoneBookDao;
import com.truecaller.demo.models.Users;

@Service
public class PhoneBookService {

	private static final Logger logger = LoggerFactory.getLogger(PhoneBookService.class);
	@Autowired
	private PhoneBookDao phoneBookDao;
	/**
	 * 
	 * @param username
	 * @return Optional<List<Users>>
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Cacheable(value = "user-cache", key = "#username")
	public Optional<List<Users>> searchUserByUserName(String username) {
		logger.info("Entering PhoneBook Serice Layer : searchUserByUserName() Method : Params {}", username);
		
		return phoneBookDao.searchUserByUserName(username);
		
		
	}

	/**
	 * 
	 * @param number
	 * @return Optional<Users>
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Optional<Users> searchUserByPhoneNumber(Long number) {

		logger.info("Entering PhoneBook Serice Layer : searchUserByPhoneNumber() Method : Params {}", number);
		return phoneBookDao.searchUserByPhoneNumber(number);
	}

}
