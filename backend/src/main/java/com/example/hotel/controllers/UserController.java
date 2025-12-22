package com.example.hotel.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.dtos.BookingDto;
import com.example.hotel.dtos.HotelDto;
import com.example.hotel.dtos.RoomDto;
import com.example.hotel.dtos.UserDto;
import com.example.hotel.service.BookingService;
import com.example.hotel.service.GetRoomDetails;
import com.example.hotel.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController()
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	private final BookingService bookingService;
	private final GetRoomDetails getRoomDetails;
	
	public UserController(UserService userService,BookingService bookingService, GetRoomDetails getRoomDetails) {
		this.userService = userService;
		this.bookingService = bookingService;
		this.getRoomDetails = getRoomDetails;
	}
	@PostMapping("register")
	public ResponseEntity<String> Register(@RequestBody UserDto userDto){
		
		return ResponseEntity.ok(userService.register(userDto));
	}
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto userDto,HttpSession session){
		
		try {
			return ResponseEntity.ok(userService.login(userDto,session));
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(null);
		}
	}
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session){
		return ResponseEntity.ok(userService.logout(session));
	}
	
	@GetMapping("/hotels")
	public ResponseEntity<List<HotelDto>> getHotels(){
		 
		return ResponseEntity.ok(userService.getHotels());
	}
	
	@PostMapping("/book")
	public ResponseEntity<String> bookHotel(@RequestBody BookingDto bookingDto)  {
		try {
			return ResponseEntity.ok(bookingService.book(bookingDto));
			
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@GetMapping("/rooms/{hotelId}")
	public ResponseEntity<List<RoomDto>> getRooms(@PathVariable Long hotelId){
		
		try {
			return ResponseEntity.ok(getRoomDetails.getRoomDetails(hotelId));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}
	@GetMapping("/bookings/{userId}")
	public ResponseEntity<List<BookingDto>> getBookings(@PathVariable Long userId){
		try {
			return ResponseEntity.ok(bookingService.getBookings(userId));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}
}
