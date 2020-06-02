package com.truecaller.demo.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.truecaller.demo.models.Users;

@Repository
public class PhoneBookDao {
	private static final Logger logger = LoggerFactory.getLogger(PhoneBookDao.class);
	
	@Autowired
	private PhoneBookRepository phoneBookRepository;
	/**
	 * 
	 * @param username
	 * @return List<Users>
	 */
	public Optional<List<Users>> searchUserByUserName(String username) {
		logger.info("Entering PhoneBook DAO Layer : searchUserByUserName() Method : Params {}",username);

		List<Users> users = phoneBookRepository.findByNameIgnoreCaseContaining(username);
		/**
		 * Sorting List Based On The Index of Pattern That We Are Trying to Find. 
		 * This Meets The Below Requirement.
		 *  
		 * Results should first show people whose names
		 * start with the search query, and then people whose names contain but don’t
		 * start with the search query.
		 */
		Collections.sort(users, (user1, user2) -> {
			Integer indexUser1 = user1.getName().indexOf(username);
			Integer indexUser2 = user2.getName().indexOf(username);
			return indexUser1.compareTo(indexUser2);
		});

		return Optional.of(users);
	}
	/**
	 * 
	 * @param number
	 * @return Optional<Users>
	 */
	public Optional<Users> searchUserByPhoneNumber(Long number) {
		
		logger.info("Entering PhoneBook DAO Layer : searchUserByPhoneNumber() Method : Params {}",number);
		
		return phoneBookRepository.findById(number);
	}
}
