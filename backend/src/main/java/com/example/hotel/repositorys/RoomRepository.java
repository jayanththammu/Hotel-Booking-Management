package com.example.hotel.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotel.dtos.RoomDto;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	boolean existsByRoomNumberAndHotel(Long roomNumber, Hotel hotel);

	Room findByRoomIdAndHotel(Long roomId, Hotel hotel);
	Room findByRoomNumberAndHotel(Long roomNumber, Hotel hotel);
	

	void deleteByRoomNumberAndHotel(Long roomNumber, Hotel hotel);

	List<Room> findByHotel(Hotel hotel);
	
	@Query(value = """
			
			select new com.example.hotel.dtos.RoomDto(
			  r.roomId,
		        r.roomNumber,
		        r.noOfShares,
		        r.roomPrice,
		        r.roomType
			
			)
			from Room r
			join r.hotel h
			where h.hotelId= :hotelId
			""")
	List<RoomDto> findRoomDetailsByHotelId(@Param("hotelId") Long hotelId);
 
		 

}
 
