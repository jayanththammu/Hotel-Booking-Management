package com.example.hotel.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminRegisterDto {
	
	private String userName;
	private String password;
	private String name;
	private String email;
	private String phoneNumber;
}
