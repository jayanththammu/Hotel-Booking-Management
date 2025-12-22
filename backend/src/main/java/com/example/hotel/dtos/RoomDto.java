package com.example.hotel.dtos;

import java.math.BigDecimal;

import com.example.hotel.dtointerfaces.RoomDetails;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = RoomDto.RoomDtoBuilder.class)
public class RoomDto implements RoomDetails{
	private Long roomId;
	private Long roomNumber;
	private Integer noOfShares;
	private  BigDecimal  roomPrice;
	private String roomType;
	
	@JsonPOJOBuilder(withPrefix = "")
	public static class RoomDtoBuilder{
		
	}
	
	
	 
}
