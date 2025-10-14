package com.example.hotel.entitys;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String hotelName;
	private String location;
	private String description;
	private String rating;
	private String email;
	private String contactNo;
	
	@OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
	private List<Room> rooms;
	
	
	 
	
	
}
