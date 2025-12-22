import React, { useContext } from "react";
import HotelComponent from "./HotelComponent";
import "../../styles/UserDashboard/HotelComponent.css";
import { useOutletContext } from "react-router-dom";
const HotelContainer = () => {
  const { hotels } = useOutletContext();
  return (
    <div className="hotel_Container">
      {hotels.map((h) => (
        <HotelComponent h={h} key={h.hotelId} />
      ))}
      ;
    </div>
  );
};

export default HotelContainer;
