package com.example.hotel.service;

import java.util.List;

import com.example.hotel.dtos.BookingDto;

public interface BookingService {

	String book(BookingDto bookingDto);
	List<BookingDto> getBookings(Long userId);
}
