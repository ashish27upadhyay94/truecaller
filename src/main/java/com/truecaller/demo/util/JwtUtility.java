package com.truecaller.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtility {
	private final String KEY = "password";

	/**
	 * 
	 * @param token
	 * @return String
	 */
	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	/**
	 * 
	 * @param token
	 * @return Date
	 */
	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}

	/**
	 * 
	 * @param token
	 * @return Boolean
	 */
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}

	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		Claims claim = extractAllClaims(token);
		return claimsResolver.apply(claim);
	}
	/**
	 * 
	 * @param userDetails
	 * @return String
	 */
	public String generateToken(UserDetails userDetails) {
		/**
		 * In claims You Can pass Payload here it is Empty
		 */
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}
	
	private String createToken(Map<String, Object> claims, String subject) {
		/**
		 * subject : User That is Being Authenticated.
		 * JWT Valid For 10 Hours
		 */
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}
}
