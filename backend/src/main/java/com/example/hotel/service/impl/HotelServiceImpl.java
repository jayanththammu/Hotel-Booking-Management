package com.example.hotel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotel.Factory.HotelFactory;
import com.example.hotel.dtos.HotelDto;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.mapper.HotelMapper;
import com.example.hotel.repositorys.HotelRepository;
import com.example.hotel.service.HotelService;

import jakarta.transaction.Transactional;

@Service
public class HotelServiceImpl implements HotelService  {

	private final HotelRepository hotelRepository;
	private final HotelFactory hotelFactory;
	private final HotelMapper hotelMapper;
	 

	public HotelServiceImpl(HotelRepository hotelRepository, HotelFactory hotelFactory, HotelMapper hotelMapper
			 ) {
		this.hotelFactory = hotelFactory;
		this.hotelRepository = hotelRepository;
		this.hotelMapper = hotelMapper;
		 
	}

	@Override
	public String addHotel(HotelDto hotelDto) {
		// TODO Auto-generated method stub
		Hotel hotel = hotelFactory.getHotelFromDto(hotelDto);
		hotelRepository.save(hotel);
		return "Hotel Saved Successfully";
	}

	@Override
	@Transactional
	public String updateHotel(Long id, HotelDto hotelDto) {
		Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel Not Found"));

		updateHotelEntity(hotel, hotelDto);
		hotelRepository.save(hotel);

		return "Hotel Updated Successfully";
	}

	private void updateHotelEntity(Hotel hotel, HotelDto hotelDto) {

		if (hotelDto.getHotelName() != null)
			hotel.setHotelName(hotelDto.getHotelName());
		if (hotelDto.getHotelDescription() != null)
			hotel.setHotelDescription(hotelDto.getHotelDescription());
		if (hotelDto.getHotelRating() != null)
			hotel.setHotelRating(hotelDto.getHotelRating());
	}
	
	@Transactional
	@Override
	public String deleteHotel(Long id) {
		hotelRepository.deleteById(id);

		return "Hotel Deleted Succussfully";
	}

	@Override
	public HotelDto getHotel(Long id) {
		Hotel hotel = hotelRepository.findById(id).orElseThrow(() ->new RuntimeException("Hotel Not found"));
		return hotelMapper.getHotelDto(hotel);

	}
	
	@Override
	public List<HotelDto> getHotels(){
		
		return hotelMapper.getHotelDtos(hotelRepository.findAllHotelWithRooms());
	}
}
