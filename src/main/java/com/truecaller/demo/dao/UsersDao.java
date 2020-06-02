package com.truecaller.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.truecaller.demo.exception.handler.UserAlreadyExistException;
import com.truecaller.demo.models.Users;

@Repository
public class UsersDao {
	@Autowired
	private UserRepository repo;

	/**
	 * <h1>save</h1> This Method Saves Users Entity To DataBase;
	 * 
	 * @param user
	 * @return user
	 */
	public Users save(Users user) {
		Optional<Users> optionalUser = repo.findById(user.getPhoneNumber());
		/**
		 * If User Already Exist In the DataBase With The A Particular Number We Are
		 * Throwing UserAlreadyExistException
		 */
		optionalUser.ifPresent(s -> {
			throw new UserAlreadyExistException("User Already Exist With This Number.Please Login In");
		});
		/**
		 * If User Does Not Exist In DataBase That Means It's A New User. Then We Are
		 * Saving User Details In the Database.
		 */
		return optionalUser.orElse(repo.save(user));
	}
}
