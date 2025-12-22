import React, { useEffect, useRef, useState } from "react";
import "../styles/AdminDashboard.css";
import {
  addHotel,
  addRoom,
  deleteHotel,
  getHotelsData,
  getRooms,
  updateHotel,
  updateRoom,
} from "../api/AdminServices";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import { useHotelMap } from "../hooks/useHotelMap";

import { logoutAdmin } from "../api/Auth";
import { useNavigate } from "react-router-dom";

import AddHotelComponent from "../components/HotelComponents/AddHotelComponent";
import UpdateHotelComponent from "../components/HotelComponents/updateHotelComponent";
import DeleteHotelComponent from "../components/HotelComponents/DeleteHotelComponent";
import AddRoomComponent from "../components/RoomComponents/AddRoomComponent";
import UpdateRoomComponent from "../components/RoomComponents/UpdateRoomComponent";
import DeleteRoomComponent from "../components/RoomComponents/DeleteRoomComponent";
const AdminDashboard = () => {
  const navigate = useNavigate();
  const [hotels, setHotels] = useState([]);

  const fetchHotels = async () => {
    const res = await getHotelsData();
    setHotels(res);

  };

  const apiCalled = useRef(false);
  useEffect(() => {
    if(apiCalled.current) return;
    apiCalled.current = true;
    fetchHotels();
  }, []);

  const hotelsMap = useHotelMap(hotels);

  // logout
  const handleLogout = async () => {
    try {
      const res = await logoutAdmin();
      if (res === "Logout Successfull") {
        localStorage.removeItem("isloggedIn");
        sessionStorage.clear();
        navigate("/");
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="admin_page">
      <header className="admin_navbar">
        <h2>Hotel Management</h2>
        <button onClick={handleLogout}>Log Out</button>
      </header>
      <ToastContainer />
      <AddHotelComponent fetchHotels={fetchHotels} />

      <UpdateHotelComponent
        hotels={hotels}
        hotelsMap={hotelsMap}
        fetchHotels={fetchHotels}
      />

      <DeleteHotelComponent
        hotelsMap={hotelsMap}
        fetchHotels={fetchHotels}
        hotels={hotels}
      />

      <AddRoomComponent hotels={hotels} hotelsMap={hotelsMap} />

      <UpdateRoomComponent
        hotels={hotels}
        hotelsMap={hotelsMap}
        fetchHotels={fetchHotels}
      />

      <DeleteRoomComponent  hotels={hotels} fetchHotels={fetchHotels} hotelsMap={hotelsMap}/>
    </div>
  );
};

export default AdminDashboard;

export function checkHotelData(name, description, rating) {
  if (name.trim() === "") {
    return false;
  }
  if (description.trim() === "") {
    return false;
  }
  if (rating.trim() === "") {
    return false;
  }
  return true;
}
export function getFilteredHotels(hotels, search) {
  return hotels.filter((h) =>
    h.hotelName.toLowerCase().includes(search.toLowerCase())
  );
}
