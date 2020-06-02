package com.truecaller.demo.user.details;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.truecaller.demo.models.UserData;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = -19635732604073237L;

	private String userName;
	private String password;
	private boolean isActive;
	private List<GrantedAuthority> authorities;

	/**
	 * 
	 * @param userdata
	 */
	public MyUserDetails(UserData userdata) {
		super();
		this.userName = userdata.getUserName();
		this.password = userdata.getPassword();
		this.isActive = userdata.isActive();
		this.authorities = Arrays.stream(userdata.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

}
