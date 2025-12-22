package com.example.hotel.dtointerfaces;

public interface UserRegisterDetails extends LoginDetails {
 
	String getNumber();
	String getEmail();
	String getFirstName();
	String getLastName();
	Long getId();
}
