package com.ssazit.util;

import java.security.Key;

import org.springframework.stereotype.Component;

import com.ssazit.dto.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	private Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("KzMb4Sf7+FicDbGDo2qQ5jjOyNAucP3BEzqiYdBV3as="));;
	
	// We need a signing key, so we'll create one just for this example. Usually
	// the key would be read from your application configuration instead.
	public String createToken(User user) {
		String jws = Jwts.builder()
				.claim("userNo", user.getUserNo())
				.claim("userId", user.getUserId())
				.claim("isAdmin", user.isAdmin())
				.signWith(key)
				.compact();
		
		return jws;
	}
	
	public JWTUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Key getKey() {
		return this.key;
	}
}
