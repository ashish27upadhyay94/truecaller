package com.truecaller.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.truecaller.demo.models.Users;

@Repository
public interface PhoneBookRepository extends JpaRepository<Users, Long> {
	
	/**
	 * The percent character (%) - which matches zero or more of any character.
	 * This Below Method Does the same Work As Method findByNameIgnoreCaseContaining
	 * And We Dont Need to Write Query For This, JPA Figures That Automatically
	 * By Method Signature.
	 * @param username
	 * @return
	 */
	@Query("SELECT user FROM Users user WHERE user.name LIKE %:username%")
	public List<Users> searchUserByUserName(String username);
	
	/**
	 * Here I don't need @Query Annotation At All I Can Do
	 * findByUserNameIgnoreCaseContaining(String username).
	 * 
	 * I cannot Change Below Method Name From <h1>findByNameIgnoreCaseContaining </h1> to
	 * <h1>findByUserNameIgnoreCaseContaining</h1> As We Don't Have userName
	 * field in Our Model Class Users.
	 * @param username
	 * @return
	 */
	
	public List<Users> findByNameIgnoreCaseContaining(String username);
	
}
