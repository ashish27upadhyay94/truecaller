package com.truecaller.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.truecaller.demo.filters.JwtFilter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtFilter jwtfilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/**
		 * Excluding Authentication For /authenticate And All URL Accessed By Swagger.
		 * 
		 * If you don't Add this -> /swagger-resources/** in your 
		 * Ant Matchers you will Not see Your Api Endpoints in Your Swagger
		 */
		http.csrf().disable().authorizeRequests()
				.antMatchers("/authenticate", "/v2/api-docs", "/swagger-ui.html", "/configuration/ui", "/webjars/**",
						"/configuration/security", "/swagger-resources","/swagger-resources/**")
				.permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Adding a Filter Before Username And Password Filter
		http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManagerBean();

	}

}
