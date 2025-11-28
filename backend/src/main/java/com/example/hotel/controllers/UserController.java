package com.example.hotel.controllers;

 
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.models.BookingDetails;
import com.example.hotel.models.BookingDto;
import com.example.hotel.models.HotelDto;
import com.example.hotel.models.UserHotelSummary;
import com.example.hotel.models.UserLogDto;
import com.example.hotel.models.UserRegiDto;
import com.example.hotel.services.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/userserver")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
	}
	
	@PostMapping("/reg")
	public String reg(@RequestBody UserRegiDto reg) throws Exception {return userService.register(reg);	}
	
	@PostMapping("/log")
	public String log(@RequestBody UserLogDto log,HttpSession session) {return userService.login(log, session);}
	
	@GetMapping("/getAllHotels")
	public List<HotelDto> viewAllHotels(){	return userService.viewAllHotels(); }
	
	 
	
	@GetMapping("/gethotelbyname/{name}")
	public HotelDto getHotelByName(@PathVariable("name") String name) {
		return userService.searchHotelByName(name);
	}
	
	@PostMapping("/book")
	public String bookHotelById(@RequestBody BookingDto booking,HttpSession session) {
		
		return userService.bookHotel(booking.getHotelId(), booking.getRoomType(), booking.getStartdate(), booking.getEndDate(), session);
	}
	
	
	@GetMapping("/hotels")
	public List<UserHotelSummary> getHotelSummary(){
		return userService.getHotels();
	}
	@GetMapping("/booking")
	public List<BookingDetails> getBookingDetails(){
		return userService.getBookingDetails();
	}
}
