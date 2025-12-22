package com.example.hotel.dtos;

import java.util.List;

import com.example.hotel.dtointerfaces.HotelDetails;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = HotelDto.HotelDtoBuilder.class)
public class HotelDto implements HotelDetails{
    
	private Long hotelId;
	private String hotelName;
	private String hotelDescription;
	private String hotelRating;
	private List<RoomDto> roomDto;
	
	@JsonPOJOBuilder(withPrefix = "")
	public static class HotelDtoBuilder {}
	 
}
