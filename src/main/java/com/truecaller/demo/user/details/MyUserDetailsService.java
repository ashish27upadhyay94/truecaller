package com.truecaller.demo.user.details;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.truecaller.demo.dao.UserDataRepository;
import com.truecaller.demo.exception.handler.UserNotFoundException;
import com.truecaller.demo.models.UserData;

@Service
public class MyUserDetailsService implements UserDetailsService {

	//@Autowired
	UserDataRepository userData;

	@Override
	public UserDetails loadUserByUsername(String username) {
		/**
		 * Uncomment This Below Code At Line 29 And Comment Lines 30,31,32 If You Don't
		 * Want To Fetch Users From DataBase In Any Case if You Want To Do So Then
		 * Create A Schema. Below Code Will Return A User With Name test And Password
		 * test
		 */
			return new User("test", "test", new ArrayList<>());

		/*
		 * Optional<UserData> user = Optional.of(userData.findByUserName(username));
		 * user.orElseThrow(() -> new UserNotFoundException("User " + username +
		 * " Cannot Be Found")); return user.map(MyUserDetails::new).get();
		 */
	}

}
