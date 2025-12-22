package com.example.hotel.entitys;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(
	    name = "room",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"hotel_id", "room_number"})
	)
public class Room { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;
	
	@Column(nullable = false)
	private Long roomNumber;
	
	private Integer noOfShares;
	
	@Column(nullable = false)	
	private  BigDecimal  roomPrice;
	
	@Column(nullable = false)
	private String roomType;
	
	@ManyToOne()
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	@OneToMany(mappedBy = "room")
	private List<Bookings> bookings;
	
	protected Room() {}
	
	private Room(Builder builder) {
		this.roomNumber = builder.roomNumber;
		this.noOfShares = builder.noOfShares;
		this.roomPrice = builder.roomPrice;
		this.roomType = builder.roomType;
		this.hotel = builder.hotel;
		this.bookings = builder.bookings;
		
	}
	
	@Getter
	@Setter
	@Accessors(chain = true)
	public static class Builder{
		
		private Long roomNumber;
		private Integer noOfShares;
		private BigDecimal  roomPrice;
		private String roomType;
		private Hotel hotel;
		private List<Bookings> bookings;
		
		public Builder() {
			
			
		}
		
		public Room build() {
			return new Room(this);
		}
		
	}
	
	
	
}
/*
create Table room(
room_id bigint primary key auto_increment,
room_number bigint unique,
no_of_shares int,
room_price decimal(10,2),
room_type varchar(10),
hotel_id bigint,
foreign key (hotel_id) references Hotel(hotel_id));

*/