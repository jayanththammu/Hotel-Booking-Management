package com.example.hotel.controllers;

 

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.models.AdminLoginDto;
import com.example.hotel.models.AdminRegisterDto;
import com.example.hotel.models.HotelDto;
import com.example.hotel.models.RoomDto;
import com.example.hotel.services.AdminService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {

	 
	private final AdminService adminSevice;
	
	public AdminController( AdminService adminSevice) {
		// TODO Auto-generated constructor stub
		this.adminSevice = adminSevice;
	}
	
	@PostMapping("/reg")
	public void register(@RequestBody AdminRegisterDto body) {
		adminSevice.register(body);
	}
	
	@PostMapping("/log")
	public String login(@RequestBody AdminLoginDto body,HttpSession session) {
		return adminSevice.login(body, session);
	}
	
	
	@PostMapping("/addhotel")
	public Long addHotel(@RequestBody HotelDto body) {
		return adminSevice.addHotel(body);
			
	}
	
	@PutMapping("/updatehotel/{id}")
	public Long updateHotel(@PathVariable Long id,@RequestBody HotelDto hotel) {
		
		return adminSevice.updateHotel(id,hotel);
	}
	
	@GetMapping("/gethotel/{id}")
	public HotelDto getHotel(@PathVariable Long id) {
		
		return adminSevice.getHotel(id);
	}
	
	@DeleteMapping("/deletehotel/{id}")
	public Long deleteHotel(@PathVariable Long id) {
		
		return adminSevice.deleteHotel(id);
	}
	
	@GetMapping("/getallhotels")
	public List<HotelDto> getAllHotels() {
		
		return adminSevice.getAllHotels();
	}
	
	@PutMapping("/updateroom/{hotelid}/{roomid}")
	public String updateRoom(@PathVariable Long hotelId,@PathVariable Long roomId,@RequestBody RoomDto roomDto) {
		return adminSevice.updateRoom(hotelId, roomId, roomDto);
	}
	
	
	 
}
