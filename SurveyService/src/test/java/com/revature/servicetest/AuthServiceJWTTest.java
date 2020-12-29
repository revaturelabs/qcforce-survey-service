package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.service.AuthService;
import com.revature.util.InvalidSurveyIdException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

/**
 * Tests for the methods of the AuthServiceJWT class. These methods require a
 * valid surveyId. If this condition is not met, these methods will throw an
 * exception.
 */
@SpringBootTest
class AuthServiceJWTTest {

	@Autowired
	AuthService authService;

	private final String SECRET = "secret";

	/**
	 * Checks AuthServiceJWT.createToken(surveyId) with a valid surveyId. If token
	 * is generated with the correct claims for surveyId, IAT, SID, BID and EXP,
	 * test passes. For temporal claims (IAT, EXP), a reasonable time range is
	 * checked in lieu of exact values.
	 */
	@Test
	void testCreateToken_withValidSurveyId() {
		int batchId = 2010;
		int surveyId = 1;
		Date before = new Date(System.currentTimeMillis());
		Date beforeExp = new Date(System.currentTimeMillis() + 1000 * 60 * 59);
		String token = this.authService.createToken(batchId, surveyId);
		Date after = new Date(System.currentTimeMillis());
		Date afterExp = new Date(System.currentTimeMillis() + 1000 * 60 * 61);
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(this.SECRET))
					.parseClaimsJws(token).getBody();
			assertEquals((int) claims.get("surveyId"), surveyId);
			assert (claims.getIssuedAt().after(before));
			assert (claims.getIssuedAt().before(after));
			assert (claims.getExpiration().after(beforeExp));
			assert (claims.getExpiration().before(afterExp));
			// TODO:
			// verify BID and SID
		} catch (MalformedJwtException exception) {
			fail(exception);
		}
	}

	/**
	 * Checks AuthServiceJWT.createToken(surveyId) with an invalid surveyId. The
	 * expected behavior is the throwing of the exception InvalidSurveyIdException.
	 */
	@Test
	void testCreateToken_withInvalidSurveyId() {
    int batchId = 2010;
		int surveyId = -1;
		// TODO:
		// find assertThrows method
		try {
			authService.createToken(batchId, surveyId);
			fail("Expected InvalidSurveyIdException to be thrown");
		} catch (InvalidSurveyIdException e) {
		} catch (Exception e) {
			fail(e);
		}
	}

}
