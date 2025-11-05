package com.example.hotel.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hotel.entitys.Bookings;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;
import com.example.hotel.entitys.User;
import com.example.hotel.models.HotelDto;
import com.example.hotel.models.RoomDto;
import com.example.hotel.models.UserLogDto;
import com.example.hotel.models.UserRegiDto;
import com.example.hotel.repositories.BookingRepo;
import com.example.hotel.repositories.HotelRepo;
import com.example.hotel.repositories.RoomRepo;
import com.example.hotel.repositories.UserRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
	
	
	private final UserRepo userRepo;
	
	private final HotelRepo hotelRepo;
	private final PasswordEncoder passwordencoder;
	private final BookingRepo bookingRepo;
	private final RoomRepo roomRepo;
	
	public UserServiceImpl(UserRepo userRepo,
						   PasswordEncoder passwordencoder,
						   HotelRepo hotelRepo,
						   BookingRepo bookingRepo,
						   RoomRepo roomRepo) {
		// TODO Auto-generated constructor stub
		this.userRepo = userRepo;
		this.passwordencoder = passwordencoder;
		this.hotelRepo = hotelRepo;
		this.bookingRepo = bookingRepo;
		this.roomRepo = roomRepo;
	}

	@Override
	public String register(UserRegiDto reg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(reg);
		if(userRepo.findByUserName(reg.getUserName()) != null) {
			throw new Exception("User Already Exists");
		}
		User user = new User();
		user
		.setName(reg.getName())
		.setUserName(reg.getUserName())
		.setPassword(passwordencoder.encode(reg.getPassword()))
		.setPhoneNumber(reg.getPhoneNumber())
		.setEmail(reg.getEmail());
		
		userRepo.save(user);
		return "User Registered Successfully";
	}

	@Override
	public String login(UserLogDto log,HttpSession session) {
		// TODO Auto-generated method stub
		
		 User user = userRepo.findByUserName(log.getUserName());
		 
			 if(user == null) {
					throw new NoSuchElementException();
				}
			 if(!passwordencoder.matches(log.getPassword(), user.getPassword())) {
				 throw new Error("Password Does'nt Match");
			 }
			 session.setAttribute("loggedinuser",user);
		
		
		return "Login Successfull";
	}

	@Override
	public List<HotelDto> viewAllHotels() {
		
		return hotelRepo.findAll().stream().map( h -> new HotelDto().setHotelName(h.getHotelName())
																	.setDescription(h.getDescription())
																	.setEmail(h.getEmail())
																	.setContactNo(h.getContactNo())
																	.setLocation(h.getLocation())
																	.setRating(h.getRating())
																	.setRooms(h.getRooms()
																						  .stream()
																						  .map(r -> new RoomDto().setRoomNo(r.getRoomNo())
																								  				 .setPricePerNight(r.getPricePerNight())
																								  				 .setRoomShares(r.getRoomShares())
																								  				 .setRoomType(r.getRoomType())
																								  				 .setStatus(r.getStatus()))
																						  .toList()))
																	.toList(); 
	}

	 
	@Override
	public String bookHotel(Long hotelId,String roomType,LocalDate startDate,LocalDate endDate,HttpSession session) {
		
		List<Room> rooms = roomRepo.findByHotelIdAndStatusAndRoomType(hotelId, false, roomType);
		
		if(rooms.size() == 0) {
			throw new Error("No Rooms Available at The Moment");
		}
		Room room = rooms.get(0);
		Hotel hotel = hotelRepo.findById(hotelId).get();
		
		Bookings bookHotel = new Bookings();
		long days = ChronoUnit.DAYS.between(startDate, endDate);
		int money = room.getPricePerNight();
		
		bookHotel.setAmount((int)(money*days))
		.setHotel(hotel)
		.setRoom(room)
		.setStartdate(startDate)
		.setEndDate(endDate)
		.setUser((User)session.getAttribute("loggedinuser"));
		
		bookingRepo.save(bookHotel);
		room.setStatus(true);
		roomRepo.save(room);
		return "";
	}

	@Override
	public String cancelBookedHotelById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelDto searchHotelByName(String name) {
		// TODO Auto-generated method stub
		
		Hotel hotel = hotelRepo.findByHotelName(name);
		
		return new HotelDto().setHotelName(hotel.getHotelName())
							 .setDescription(hotel.getDescription())
							 .setEmail(hotel.getEmail())
							 .setLocation(hotel.getLocation())
							 .setRating(hotel.getRating())
							 .setContactNo(hotel.getContactNo())
							 .setRooms( hotel.getRooms().stream()
									 					.map(r -> new RoomDto().setRoomNo(r.getRoomNo())
									 													.setPricePerNight(r.getPricePerNight())
									 													.setRoomShares(r.getRoomShares())
									 													.setRoomType(r.getRoomType())
									 													.setStatus(r.getStatus()))
									 					.toList());
	}

}
