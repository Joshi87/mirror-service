package org.ioak.mirror.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.ioak.mirror.domain.TokenKeyContainer;
import org.ioak.mirror.domain.TokenKey;
import org.ioak.mirror.domain.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private String secret;

	//retrieve username from jwt token
	public String getUsernameFromToken(String token, String requestSecretKeyHeader) {
		return getClaimFromToken(token, Claims::getSubject, requestSecretKeyHeader);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver, String requestSecretKeyHeader) {
		final Claims claims = getAllClaimsFromToken(token, requestSecretKeyHeader);
		return claimsResolver.apply(claims);
	}
	//for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token, String requestSecretKeyHeader) {
		return Jwts.parser().setSigningKey(TokenKeyContainer.get(requestSecretKeyHeader).getValue()).parseClaimsJws(token).getBody();
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token, String requestSecretKeyHeader) {
		final Date expiration = getExpirationDateFromToken(token,requestSecretKeyHeader);
		return expiration.before(new Date());
	}

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token, String requestSecretKeyHeader) {
		return getClaimFromToken(token, Claims::getExpiration, requestSecretKeyHeader);
	}

	//generate token for user
	public String[] generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, user.getId());
	}


	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string
	private String[] doGenerateToken(Map<String, Object> claims, String subject) {
		TokenKey tokenKey = TokenKeyContainer.get();
		// lock your JWT using tokenKey.getValue()
		secret = tokenKey.getValue();

		return new String[] {"Bearer "+Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact() , tokenKey.getKey()};
	}



	//validate token
	public Boolean validateToken(String token, User userDetails, String requestSecretKeyHeader) {
		final String userId = getUsernameFromToken(token, requestSecretKeyHeader);
		return (userId.equals(userDetails.getId()) && !isTokenExpired(token, requestSecretKeyHeader));
	}
}
