package com.example.hotel.models;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HotelDto {
	
	private String hotelName;
	private String location;
	private String description;
	private String rating;
	private String email;
	private String contactNo;
	private List<RoomDto> rooms;
	
	 
	
	

}
