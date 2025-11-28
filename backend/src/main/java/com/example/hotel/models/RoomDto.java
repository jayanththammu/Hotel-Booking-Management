package com.example.hotel.models;

 
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoomDto {
	
	public RoomDto() {
		
	}
	
	private Integer roomNo;
	private Integer roomShares;
	private String roomType;
	private Integer pricePerNight;
	private Boolean status;
	
	 
	
	
}
