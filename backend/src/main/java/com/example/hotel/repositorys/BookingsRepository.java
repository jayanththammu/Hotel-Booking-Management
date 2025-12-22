package com.example.hotel.repositorys;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.hotel.entitys.Bookings;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {

	List<Bookings> findByHotelAndRoomAndCheckInDateIsLessThanAndCheckOutDateIsGreaterThanEqual(Hotel hotel, Room room,
			LocalDate checkOutDate, LocalDate checkInDate);
	
 
  
	@Query(value = """
			  SELECT b
			 FROM Bookings b
			 JOIN FETCH b.user
			 JOIN FETCH b.room
			 JOIN FETCH b.hotel
			 WHERE b.user.id = :userId

			""")
	List<Bookings> findByUser(@PathVariable("userId") Long userId);

 
}
