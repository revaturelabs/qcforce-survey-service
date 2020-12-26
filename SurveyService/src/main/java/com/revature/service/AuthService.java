package com.revature.service;

public interface AuthService {
	
	public String createToken(int surveyId);
	
	public int surveyIdFromToken(String token);
	
	public Boolean validateToken(String token);
	
}
