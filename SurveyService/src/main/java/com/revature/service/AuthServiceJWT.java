package com.revature.service;

import org.springframework.stereotype.Service;

/**
 * 
 * This service contains methods related to handling a JWT.
 *
 */

@Service
public class AuthServiceJWT implements AuthService {

	/**
	 * This method creates a JWT based on the provided surveyId (int). If the
	 * provided surveyId is invalid, the method throws InvalidSurveyIdException.
	 * Created JWT includes claims for IAT, EXP, SID, BID and surveyId and signs it
	 * with a secret.
	 * 
	 * @param surveyId Represents a valid survey's id.
	 * @return Returns a String that represents the created JWT.
	 */
	@Override
	public String createToken(int surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

}