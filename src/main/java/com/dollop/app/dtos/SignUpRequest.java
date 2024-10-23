package com.dollop.app.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequest 
{
	private String username;
    private String email;
    private String password;
}
