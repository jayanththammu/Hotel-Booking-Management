package com.example.hotel.service;

import java.util.List;

import com.example.hotel.dtos.HotelDto;

public interface HotelService  {
	String addHotel(HotelDto hotelDto);
	String updateHotel(Long id,HotelDto hoteDto);
	HotelDto getHotel(Long id);
	String deleteHotel(Long id);
	public List<HotelDto> getHotels();
}
