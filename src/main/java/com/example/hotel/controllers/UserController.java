package com.example.hotel.controllers;

 
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.models.HotelDto;
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
	
	@GetMapping("/bookHotel/{hotelid}/{roomid}/{days}")
	public String bookHotel(@PathVariable("hotelid") Long hotelId,@PathVariable("roomid") Long roomId,@PathVariable("days") int days,HttpSession session) throws Exception {
		return userService.bookHotelById(hotelId, roomId, days, session);
	}
	
	@GetMapping("/gethotelbyname/{name}")
	public HotelDto getHotelByName(@PathVariable("name") String name) {
		return userService.searchHotelByName(name);
	}
	
}
