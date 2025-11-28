package com.example.hotel.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminLoginDto {
	
	private String userName;
	private String password;

}
