package com.truecaller.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.truecaller.demo.dao.UsersDao;
import com.truecaller.demo.models.Users;

@Service
public class UserService {

	@Autowired
	UsersDao usersDao;

	/**
	 * 
	 * @param user
	 * @return Users
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Users saveUser(Users user) {
		return usersDao.save(user);
	}

}
