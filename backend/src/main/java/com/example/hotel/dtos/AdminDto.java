package com.example.hotel.dtos;

import com.example.hotel.dtointerfaces.AdminRegisterDetails;

import lombok.Data;

@Data
public class AdminDto implements AdminRegisterDetails{
	private String userName;
	private String password;
	private String number;
	private String email;
	
 	 
}
