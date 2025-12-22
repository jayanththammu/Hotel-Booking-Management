package com.example.hotel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotel.dtos.AdminDto;
import com.example.hotel.dtos.HotelDto;
import com.example.hotel.dtos.RoomDto;
import com.example.hotel.service.AdminService;
import com.example.hotel.service.HotelService;
import com.example.hotel.service.RoomService;
import com.example.hotel.service.admin.AdminAuth;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {
	private final AdminAuth adminAuth;
	private final HotelService hotelService;
	private final RoomService roomService;
 

	public AdminServiceImpl(AdminAuth adminAuth, HotelService hotelService,RoomService roomService ) {
		this.adminAuth = adminAuth;
		this.hotelService = hotelService;
		this.roomService = roomService;
	 

	}

	@Override
	public String login(AdminDto adminDto, HttpSession session) {

		return adminAuth.login(adminDto, session);

	}

	@Override
	public String register(AdminDto adminDto) {

		return adminAuth.register(adminDto);
	}
	
	@Override
	public String logOut(HttpSession session) {
		// TODO Auto-generated method stub
		return adminAuth.logOut(session);
	}
	@Override
	public String addHotel(HotelDto hotelDto) {

		return hotelService.addHotel(hotelDto);
	}

	@Override
	public String deleteHotel(Long id) {

		return hotelService.deleteHotel(id);
	}

	@Override
	public HotelDto getHotel(Long id) {

		return hotelService.getHotel(id);

	}

	@Override
	public String updateHotel(Long id, HotelDto hotelDto) {

		return hotelService.updateHotel(id, hotelDto);
	}
	
	@Override
	public List<HotelDto> getHotels(){
		return hotelService.getHotels();
	}

	@Override
	public String addRoom(Long hotelId, RoomDto room) {
		// TODO Auto-generated method stub
		return roomService.addRoom(hotelId, room);
	}
	
	@Override
	public String updateRoom(Long hotelId,Long roomId,RoomDto room) {
		try {
			return roomService.updateRoom(hotelId, roomId, room);
			
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
	}

	@Override
	public String delRoom(Long hotelId, Long roomNo) {
		// TODO Auto-generated method stub
		return roomService.delRoom(hotelId, roomNo);
	}

	@Override
	public RoomDto getRoom(Long hotelId, Long roomNo) {
		// TODO Auto-generated method stub
		return roomService.getRoom(hotelId, roomNo);
	}

	@Override
	public List<RoomDto> getAllRooms(Long hotelId) {
		// TODO Auto-generated method stub
		return roomService.getAllRooms(hotelId);
	}

}
