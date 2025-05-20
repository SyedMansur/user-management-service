package com.wipro.usermanagement.component;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class JwtUtil {

	
	
	private final String SECRET = "j,LWg=^7t*>0e9vTU}q<54,OOng0Zu#htbhjxehbjwef734rbjhbj43vrj3brxjbjjgvx3vjgvjxg2jgvxg23gvjgvjgjjgvxjgvjgfx2rxrx";
	private final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
	private final long EXPIRATION_TIME = 1000 * 60 * 60;

	public String generateToken(String userName) {

		return Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRET_KEY, SignatureAlgorithm.HS512)
				.compact();
	}

	public String extractUserName(String token) {

		return Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public boolean validateToken(String token) {
		try {

			Jwts.parserBuilder()
			.setSigningKey(SECRET_KEY)
			.build()
			.parseClaimsJws(token);
			return true;
			
		} catch (Exception ex) {
			
			return false;
		}
	}
}
