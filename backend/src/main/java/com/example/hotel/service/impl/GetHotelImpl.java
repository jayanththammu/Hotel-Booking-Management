package com.example.hotel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotel.dtos.HotelDto;
import com.example.hotel.mapper.HotelMapper;
import com.example.hotel.repositorys.HotelRepository;
import com.example.hotel.service.GetHotelInterface;

@Service
public class GetHotelImpl implements GetHotelInterface {
	
	private final  HotelRepository hotelRepository;
	private final HotelMapper hotelMapper;
	
	public GetHotelImpl(HotelRepository hotelRepository, HotelMapper hotelMapper) {
		
		this.hotelRepository = hotelRepository;
		this.hotelMapper =hotelMapper;
	}

	@Override
	public List<HotelDto> getHotels(){
		
		return hotelMapper.getHotelDtos(hotelRepository.findAllHotelWithRooms());
	}
}
