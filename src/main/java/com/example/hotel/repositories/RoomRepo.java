package com.example.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entitys.Room;

public interface RoomRepo extends JpaRepository<Room, Long> {
	Room findByHotelIdAndId(Long hotelId, Long id);

}
