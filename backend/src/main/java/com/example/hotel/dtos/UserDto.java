package com.example.hotel.dtos;

 
import com.example.hotel.dtointerfaces.UserRegisterDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserDto implements  UserRegisterDetails{
	private String userName;
	private String password;
	private String number;
	private String email;
	private String firstName;
	private String lastName;
	private Long id;
	
	
 	 
}
