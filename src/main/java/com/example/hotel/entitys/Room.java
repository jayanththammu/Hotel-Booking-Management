package com.example.hotel.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer roomNo;
	private Integer roomShares;
	private String roomType;
	private Integer pricePerNight;
	private Boolean status;
	
	@ManyToOne()
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	
	 
	
	
	
	
}