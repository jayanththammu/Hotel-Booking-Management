package com.example.hotel.dtointerfaces;

import java.math.BigDecimal;

public interface RoomDetails {
	Long getRoomId();
	Long getRoomNumber();
	Integer getNoOfShares();
    BigDecimal  getRoomPrice();
	String getRoomType();
	  
}
 