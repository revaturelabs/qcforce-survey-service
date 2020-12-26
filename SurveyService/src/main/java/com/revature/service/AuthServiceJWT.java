package com.revature.service;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceJWT implements AuthService {

	@Override
	public String createToken(int surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int surveyIdFromToken(String token) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean validateToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
