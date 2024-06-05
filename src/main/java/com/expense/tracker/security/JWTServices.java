package com.expense.tracker.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.expense.tracker.service.MyUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTServices {
	@Autowired
	MyUserDetailsService service;

	public final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B625064536756685970";

	public UserDetails getUserFromToken(String token) {
		String userName = getUserNameFromToken(token);
		return service.loadUserByUsername(userName);
	}

	public String getUserNameFromToken(String token) {
		return ExtractClaim(token, Claims::getSubject);

	}

	public String issueToken(UserDetails userDetails) {

		return Jwts.builder().setSubject(userDetails.getUsername()).signWith(getsigningkey(), SignatureAlgorithm.HS256)
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60)).compact();

	}

	public Claims ExtractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getsigningkey()).build().parseClaimsJws(token).getBody();

	}

	public <T> T ExtractClaim(String token, Function<Claims, T> ClaimResolver) {
		final Claims claims = ExtractAllClaims(token);
		return ClaimResolver.apply(claims);
	}

	private Key getsigningkey() {
		byte[] ketBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(ketBytes);
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String userName = getUserNameFromToken(token);
		return userName.equals(userDetails.getUsername()) && !isExpiredToken(token);
	}

	private boolean isExpiredToken(String token) {
		Date Expiry = ExtractClaim(token, Claims::getExpiration);
		return Expiry.before(new Date());
	}

}
