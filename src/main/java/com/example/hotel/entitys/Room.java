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
	
	protected Room() {}
	
	private Room(RoomBuilder builder) {
		this.roomNo = builder.roomNo;
		this.roomShares = builder.roomShares;
		this.roomType = builder.roomType;
		this.pricePerNight = builder.pricePerNight;
		this.status = builder.status;
		this.hotel = builder.hotel;
		 
		
	}
	@Data
	@Accessors(chain = true)
	public static class RoomBuilder {
		private Long id;
		
		private Integer roomNo;
		private Integer roomShares;
		private String  roomType;
		private Integer pricePerNight;
		private Boolean status;
		private Hotel hotel;
		
		
		public Room build() {
			return new Room(this);
		}
	}
	
	public static RoomBuilder from(Room room) {
		return new RoomBuilder()
				.setPricePerNight(room.getPricePerNight())
				.setRoomNo(room.getRoomNo())
				.setRoomType(room.getRoomType())
				.setStatus(room.getStatus())
				.setRoomShares(room.getRoomShares());
	}
	
	 
	
	
	
	
}