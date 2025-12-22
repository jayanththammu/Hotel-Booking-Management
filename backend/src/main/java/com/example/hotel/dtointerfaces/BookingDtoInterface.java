package com.example.hotel.dtointerfaces;

import java.time.LocalDate;

public interface BookingDtoInterface {

	Long getUserId();
	Long getHotelId();
	Long getRoomId();
	LocalDate getCheckInDate();
	LocalDate getCheckOutDate();
	Double getAmount();
	String getHotelName();
	Long getRoomNumber();
	String getCheckIn();
	String getCheckOut();
}
