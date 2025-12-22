import React from "react";
import "../../styles/UserDashboard/HotelComponent.css";
import hotel from "../../assets/User/hotel1.jpg";
import { Link } from "react-router-dom";
const HotelComponent = ({ h }) => {
  return (
    <div className="hotel">
      <div className="hotel_img">
        <img src={hotel} alt="" />
      </div>
      <div className="hotel_content">
        <div className="hotel_header">{h.hotelName}</div>
        <p>{h.hotelDescription}</p>
      </div>
      <Link to={`hotel/${h.hotelId}`}>
        <button className="hotel_btn">View More</button>
      </Link>
    </div>
  );
};

export default HotelComponent;
