package com.revature.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This service receives requests from the distribution service to create a JWT.
 */
@Service
public class AuthServiceJWT implements AuthService {

	/**

	 * This method creates and returns a Json Web Token given a surveyId and batchID
	 * with various claims. Sets an IAT (issued at) claim for the current time in
	 * milliseconds. Sets an EXP (expiration) claim for 15 minutes past the IAT
	 * time. Sets a surveyID claim to hold the identifier for the distributed
	 * survey. Sets a batchId claim to hole the identifier for the specified batch.
	 * 
	 * @param surveyId the identifier for the distributed survey
	 * @param batchId  the identifier for the specified batch receiving the emails.
	 */
	@Override
	public String createToken(int surveyId, int batchId) {

	  return null;
	}

}
