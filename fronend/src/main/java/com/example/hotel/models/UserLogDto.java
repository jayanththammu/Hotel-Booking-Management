package com.example.hotel.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserLogDto {

	private String userName;
	private String password;
}