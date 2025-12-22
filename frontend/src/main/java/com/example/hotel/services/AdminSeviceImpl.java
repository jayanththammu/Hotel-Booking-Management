package com.example.hotel.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.hotel.Mapper.HotelMapper;
import com.example.hotel.entitys.Admin;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;
import com.example.hotel.factory.HotelFactory;
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
	
	private final HotelFactory factory;
	private final HotelMapper mapper;
	
 

	public AdminSeviceImpl(AdminRepo adminRepo, HotelRepo hotelRepo, PasswordEncoder encoder,RoomRepo roomRepo
							,HotelFactory factory
							,HotelMapper mapper) {

		this.adminRepo = adminRepo;
		this.hotelRepo = hotelRepo;
		this.encoder = encoder;
		this.roomRepo = roomRepo;
		this.factory = factory;
		this.mapper = mapper;
		 
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

		Hotel hotel =  factory.createHotelFromDto(body);
		hotelRepo.save(hotel);
 

		return hotel.getId();
	}

	@Override
	@Transactional
	public Long updateHotel(Long id, HotelDto hotelUpdate) {

 
		
		Hotel existing = hotelRepo.findById(id).orElseThrow(()-> new Error("Hotel Not Found"));
		
		Hotel updated =  Hotel.from(existing)
								.setHotelName(hotelUpdate.getHotelName() != null ? hotelUpdate.getHotelName() : existing.getHotelName())
								.setContactNo(hotelUpdate.getContactNo() != null ? hotelUpdate.getContactNo() : existing.getContactNo())
								.setDescription(hotelUpdate.getDescription() != null ? hotelUpdate.getDescription() : existing.getDescription())
								.setEmail(hotelUpdate.getEmail() != null ? hotelUpdate.getEmail() : existing.getEmail())
								.setLocation(hotelUpdate.getLocation() != null ? hotelUpdate.getLocation() : existing.getLocation())
								.setRating(hotelUpdate.getRating() != null ? hotelUpdate.getRating() : existing.getRating())
		
						.build();
		
		hotelRepo.save(updated);
		return updated.getId();
		}
	
	@Override
	@Transactional
	public Integer updateRoom(Long hotelId,Integer roomNo,RoomDto roomDto) {
		
		Room existing = roomRepo.findByHotelIdAndRoomNo(hotelId, roomNo);
		
		Room updated = Room.from(existing).setRoomShares(roomDto.getRoomShares() != null ? roomDto.getRoomShares(): existing.getRoomShares())
										  .setPricePerNight(roomDto.getPricePerNight() != null ? roomDto.getPricePerNight(): existing.getPricePerNight())
										  .setRoomType(roomDto.getRoomType() != null ? roomDto.getRoomType(): existing.getRoomType())
										  .setStatus(roomDto.getStatus() != null ? roomDto.getStatus(): existing.getStatus())
										  
				.build();
		 
			 
	 
		 
		roomRepo.save(updated);
		
		return updated.getRoomNo();
		
	}

	@Override
	public HotelDto getHotel(Long id) {
		// TODO Auto-generated method stub

		Optional<Hotel> optional = hotelRepo.findById(id);

		if (optional.isEmpty()) {
			throw new Error("Wrong ID");
		}
		HotelDto hotelDto = mapper.CreateDto(optional.get());
		return hotelDto;
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
								  .map(mapper::CreateDto)
								  .toList();

		
	}
	
	 

}
