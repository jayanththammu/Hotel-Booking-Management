package com.example.hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entitys.Room;

public interface RoomRepo extends JpaRepository<Room, Long> {
	Room findByHotelIdAndId(Long hotelId, Long id);
	List<Room> findByHotelIdAndStatusAndRoomType(Long hotelId,Boolean status,String roomType);
	Room findByHotelIdAndRoomNo(Long hotelId,Integer roomNo);

}
