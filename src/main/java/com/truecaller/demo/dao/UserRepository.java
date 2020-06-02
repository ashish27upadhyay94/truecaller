package com.truecaller.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truecaller.demo.models.Users;

/**
 * 	@author ashish
 * 
 *  This Interface Extends JPARepository,
 *  The Purpose of Extending JPA Repository is that
 *  We Don't Need to Write Boiler-Plate Code e.g findAll
 *  deleteById, Save, FindByID.
 *  
 *  All you Need to Do is @autowire this Class in your DAO
 *  Layer and Use the Methods.
 *  
 */
public interface UserRepository extends JpaRepository<Users, Long> {

}
