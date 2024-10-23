package com.dollop.app.service;

import java.util.Map;
import com.dollop.app.dtos.AuthResponse;
import com.dollop.app.dtos.SignInRequest;
import com.dollop.app.dtos.SignUpRequest;
import com.dollop.app.dtos.UserDto;
import com.dollop.app.entity.User;
import com.dollop.app.exception.DuplicateEntryException;

public interface IAuthResponseService 
{
	UserDto signUp(SignUpRequest signUpRequest);
    AuthResponse signIn(SignInRequest signInRequest);
    
    Map<String, Object> loginUser(String userPassword, String userName);
	Integer signUp(User user) throws DuplicateEntryException;
}
