package com.example.hotel.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.hotel.entitys.Hotel;
 
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
	@Query("""
		       SELECT DISTINCT h 
		       FROM Hotel h 
		       LEFT JOIN FETCH h.rooms
		       """)
		List<Hotel> findAllHotelWithRooms();

}
