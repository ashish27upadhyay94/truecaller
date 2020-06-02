package com.truecaller.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truecaller.demo.models.UserData;
/**
 * 
 * @author ashish
 *
 */
@Repository
public interface UserDataRepository extends JpaRepository<UserData, Integer> {
	/**
	 *
	 * @param userName
	 * @return Optional<UserData>
	 */
	UserData findByUserName(String userName);
}
