package com.example.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entitys.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Long> {
	
	Hotel findByHotelName(String hotelName);

	 
}
