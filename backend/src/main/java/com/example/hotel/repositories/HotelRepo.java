package com.example.hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hotel.entitys.Hotel;
import com.example.hotel.models.BookingDetails;
import com.example.hotel.models.HotelRoomCount;
import com.example.hotel.models.HotelSummary;
import com.example.hotel.models.UserHotelSummary;

public interface HotelRepo extends JpaRepository<Hotel, Long> {
	
	Hotel findByHotelName(String hotelName);
	
	List<HotelSummary> findAllBy();
	
	@Query(value = """
		       SELECT h.id as id, h.hotel_name AS hotelName,
            COUNT(r.id) AS rooms,
            GROUP_CONCAT(r.room_no) AS roomNos
     FROM hotel h
     LEFT JOIN room r ON r.hotel_id = h.id
     GROUP BY h.id
     """, nativeQuery = true)
	List<HotelRoomCount> findHotelRoomCount();
	
	@Query(value = """
			SELECT 
    h.hotel_name,
    h.description,
    h.id,
    h.rating,
    MIN(r.price_per_night) AS min_price,
    GROUP_CONCAT(DISTINCT r.room_type) AS room_types
FROM hotel h
JOIN room r 
    ON h.id = r.hotel_id
GROUP BY h.id;

			""",nativeQuery = true)
	List<UserHotelSummary> findAllUserHotel();
	
	@Query(value = """
			select
			 h.hotel_name,
			 h.id,group_concat(distinct r.room_type )  as "room_details",
			 group_concat(distinct concat(r.room_type,' ',r.room_shares," ",r.price_per_night)) as room_price_shares
			from hotel h
			join room r on h.id = r.hotel_id
			group by h.id;
			""",nativeQuery = true)
	List<BookingDetails> findAllBookingDetails();
	
	
	
		 
    
 
}
