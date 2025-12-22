package com.example.hotel.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.HotelBookingApplication;
import com.example.hotel.dtos.AdminDto;
import com.example.hotel.dtos.HotelDto;
import com.example.hotel.dtos.RoomDto;
import com.example.hotel.repositorys.BookingsRepository;
import com.example.hotel.service.AdminService;

import jakarta.servlet.http.HttpSession;

@RestController()
@RequestMapping("/admin")
public class AdminController {

    private final HotelBookingApplication hotelBookingApplication;

	private final BookingsRepository bookingsRepository;

	private final AdminService adminService;

	public AdminController(AdminService adminService, BookingsRepository bookingsRepository, HotelBookingApplication hotelBookingApplication) {
		this.adminService = adminService;
		this.bookingsRepository = bookingsRepository;
		this.hotelBookingApplication = hotelBookingApplication;
	}

	@PostMapping("register")
	public ResponseEntity<String> Register(@RequestBody AdminDto adminDto) {

		return ResponseEntity.ok(adminService.register(adminDto));
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AdminDto adminDto, HttpSession session) {

		return ResponseEntity.ok(adminService.login(adminDto, session));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logOut(HttpSession session){
		return ResponseEntity.ok(adminService.logOut(session));
	}

	@PostMapping("/addhotel")
	public ResponseEntity<String> addHotel(@RequestBody HotelDto hotelDto) {

		return ResponseEntity.ok(adminService.addHotel(hotelDto));
	}

	@PutMapping("/updatehotel/{id}")
	public ResponseEntity<String> updateHotel(@PathVariable Long id, @RequestBody HotelDto hotelDto) {

		return ResponseEntity.ok(adminService.updateHotel(id, hotelDto));
	}

	@DeleteMapping("/deletehotel/{id}")
	public ResponseEntity<String> deleteHotel(@PathVariable Long id) {

		return ResponseEntity.ok(adminService.deleteHotel(id));
	}

	@GetMapping("/gethotel/{id}")
	public ResponseEntity<HotelDto> getHotel(@PathVariable Long id) {

		return ResponseEntity.ok(adminService.getHotel(id));
	}

	@GetMapping("/gethotels")
	public ResponseEntity<List<HotelDto>> getHotels() {

		return ResponseEntity.ok(adminService.getHotels());
	}

	@PostMapping("/addroom/{id}")
	public ResponseEntity<String> addRoom(@PathVariable Long id, @RequestBody RoomDto room) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(adminService.addRoom(id, room));
	}

	@PutMapping("/updateroom/{id}/{roomid}")
	public ResponseEntity<String> updateRoom(@PathVariable("id") Long id, @PathVariable("roomid") Long roomid,
			@RequestBody RoomDto roomDto) {
		 
		return ResponseEntity.ok(adminService.updateRoom(id, roomid, roomDto));
	}
	@DeleteMapping("/delroom/{hotelid}/{roomno}")
	public ResponseEntity<String>  delRoom(@PathVariable("hotelid")Long hotelId,@PathVariable("roomno") Long roomNo) {
	 
		return ResponseEntity.ok(adminService.delRoom(hotelId, roomNo));
	}

	@GetMapping("/getroom/{hotelid}/{roomid}")
	public ResponseEntity<RoomDto> getRoom(@PathVariable("hotelid") Long hotelId,@PathVariable("roomid") Long roomId) {
		 
		return ResponseEntity.ok(adminService.getRoom(hotelId, roomId));
	}

	@GetMapping("/getrooms/{hotelid}")
	public ResponseEntity<List<RoomDto>> getAllRooms(@PathVariable("hotelid")Long hotelId) {
	 
		return ResponseEntity.ok(adminService.getAllRooms(hotelId));
	}

}
