package com.example.hotel.services;

import java.time.LocalDate;
import java.util.List;

import com.example.hotel.models.BookingDetails;
import com.example.hotel.models.HotelDto;
import com.example.hotel.models.UserHotelSummary;
import com.example.hotel.models.UserLogDto;
import com.example.hotel.models.UserRegiDto;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	String register(UserRegiDto reg) throws Exception;
	String login(UserLogDto log,HttpSession session);
	List<HotelDto> viewAllHotels();
 
	String bookHotel(Long hotelId,String roomType,LocalDate startDate,LocalDate endDate,HttpSession session);
	String cancelBookedHotelById(Long id);
	HotelDto searchHotelByName(String name);
	List<UserHotelSummary> getHotels();
	List<BookingDetails> getBookingDetails();
}