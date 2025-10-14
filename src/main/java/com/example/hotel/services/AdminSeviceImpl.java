package com.example.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hotel.entitys.Admin;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;
import com.example.hotel.models.AdminLoginDto;
import com.example.hotel.models.AdminRegisterDto;
import com.example.hotel.models.HotelDto;
import com.example.hotel.models.RoomDto;
import com.example.hotel.repositories.AdminRepo;
import com.example.hotel.repositories.HotelRepo;
import com.example.hotel.repositories.RoomRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class AdminSeviceImpl implements AdminService {

  

	private final AdminRepo adminRepo;

	private final HotelRepo hotelRepo;

	private final PasswordEncoder encoder;
	
	private final RoomRepo roomRepo;

	public AdminSeviceImpl(AdminRepo adminRepo, HotelRepo hotelRepo, PasswordEncoder encoder,RoomRepo roomRepo) {

		this.adminRepo = adminRepo;
		this.hotelRepo = hotelRepo;
		this.encoder = encoder;
		this.roomRepo = roomRepo;
		 
	}

	public void register(AdminRegisterDto body) {

		Admin admin = new Admin();

		admin.setName(body.getName())
			 .setUserName(body.getUserName())
			 .setPassword(encoder.encode(body.getPassword()))
			 .setPhoneNumber(body.getPhoneNumber())
			 .setEmail(body.getEmail());

		adminRepo.save(admin);

	}

	public String login(AdminLoginDto body, HttpSession session) {

		Admin admin = adminRepo.findByUserName(body.getUserName());
		if (admin == null) {
			return "Wrong UserName";
		}
		if (!encoder.matches(body.getPassword(), admin.getPassword())) {
			return "Wrong Password";
		}

		session.setAttribute("LoggedInAdmin", admin);

		return "Login Successful";

	}

	@Transactional
	public Long addHotel(HotelDto body) {

		Hotel hotel = new Hotel();
		

			hotel.setHotelName(body.getHotelName())
				 .setDescription(body.getDescription())
				 .setContactNo(body.getContactNo())
				 .setEmail(body.getEmail())
				 .setLocation(body.getLocation())
				 .setRating(body.getRating())
				 .setRooms( body.getRooms()
								   .stream()
								   .map( r -> new Room().setHotel(hotel)
										   				.setRoomNo(r.getRoomNo())
										   				.setRoomShares(r.getRoomShares())
										   				.setRoomType(r.getRoomType())
										   				.setStatus(r.getStatus())
										   				.setPricePerNight(r.getPricePerNight()))
								   .toList());
			
			hotelRepo.save(hotel);
 

		return hotel.getId();
	}

	@Override
	@Transactional
	public Long updateHotel(Long id, HotelDto hotelUpdate) {

		Optional<Hotel> optionalHotel = hotelRepo.findById(id);

		if (!optionalHotel.isPresent()) {
			throw new Error("Wrong Id,No Hotel Found");
		}

		Hotel hotel = optionalHotel.get();

		if (hotelUpdate.getHotelName() != null) { hotel.setHotelName(hotelUpdate.getHotelName());}
		if (hotelUpdate.getContactNo() != null) {hotel.setContactNo(hotelUpdate.getContactNo());}
		if (hotelUpdate.getEmail() != null) {	hotel.setEmail(hotelUpdate.getEmail());}
		if (hotelUpdate.getLocation() != null) {	hotel.setLocation(hotelUpdate.getLocation());}
		if (hotelUpdate.getRating() != null) {	hotel.setRating(hotelUpdate.getRating());}
		if (hotelUpdate.getDescription() != null) {	hotel.setDescription(hotelUpdate.getDescription());}	

		hotelRepo.save(hotel);

		return hotel.getId();
	}

	@Override
	public HotelDto getHotel(Long id) {
		// TODO Auto-generated method stub

		Optional<Hotel> optional = hotelRepo.findById(id);

		if (optional.isEmpty()) {
			throw new Error("Wrong ID");
		}
		Hotel hotel = optional.get();

		HotelDto hoteldto = new HotelDto();

		hoteldto.setHotelName(hotel.getHotelName()).setLocation(hotel.getLocation())
				.setDescription(hotel.getDescription()).setEmail(hotel.getEmail()).setRating(hotel.getRating())
				.setContactNo(hotel.getContactNo())
				.setRooms(hotel.getRooms()
							   .stream()
							   .map( r -> new RoomDto()
									   				.setPricePerNight(r.getPricePerNight())
									   				.setRoomNo(r.getRoomNo())
									   				.setRoomShares(r.getRoomShares())
									   				.setRoomType(r.getRoomType())
									   				.setStatus(r.getStatus()))
							   .toList());

		 
		return hoteldto;
	}

	@Override
	@Transactional
	public Long deleteHotel(Long id) {
		// TODO Auto-generated method stub
		Optional<Hotel> optional = hotelRepo.findById(id);

		if (optional.isEmpty()) {
			throw new Error("Wrong ID");
		}
		Hotel hotel = optional.get();
		Long hotelId = hotel.getId();

		hotelRepo.deleteById(hotelId);

		return hotelId;
	}

	@Override
	@Transactional
	public List<HotelDto> getAllHotels() {

		return hotelRepo.findAll().stream()
								  .map( h -> new HotelDto().setHotelName(h.getHotelName())
										  				   .setDescription(h.getDescription())
										  				   .setContactNo(h.getContactNo())
										  				   .setEmail(h.getEmail())
										  				   .setRating(h.getRating())
										  				   .setRooms( h.getRooms().stream().
										  						   map( r -> new RoomDto().setRoomNo(r.getRoomNo())
										  								   				  .setPricePerNight(r.getPricePerNight())
										  								   				  .setRoomShares(r.getRoomShares())
										  								   				  .setRoomType(r.getRoomType())
										  								   				  .setStatus(r.getStatus()))
										  						   .toList()))
								  .toList();

		
	}
	
	public String updateRoom(Long hotelId,Long roomId,RoomDto roomDto) {
		
		Room room = roomRepo.findByHotelIdAndId(hotelId, roomId);
		
		if(roomDto.getRoomNo() != null) { room.setRoomNo(roomDto.getRoomNo());}
		if(roomDto.getPricePerNight() != null) { room.setPricePerNight(roomDto.getPricePerNight());}
		if(roomDto.getRoomShares() != null) { room.setRoomShares(roomDto.getRoomShares());}
		if(roomDto.getStatus() != null) { room.setStatus(roomDto.getStatus());}
		
		roomRepo.save(room);
		
		return "Room Updated Successfully";	 
	}

}
