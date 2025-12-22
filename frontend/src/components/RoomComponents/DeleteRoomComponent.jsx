import React, { useEffect, useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import Search from "../../assets/subtract.svg";
import backarrow from "../../assets/backarrow.svg";
import { getFilteredHotels } from "../../pages/AdminDashboard";
import "../../styles/RoomDeleteComponent.css";
import { deleteRoom } from "../../api/AdminServices";

const DeleteRoomComponent = ({ hotels, hotelsMap, fetchHotels }) => {
  const [filteredHotels, setFilteredHotels] = useState(hotels);
  const [search, setSearch] = useState("");
  const [selectedHotel, setSelectedHotel] = useState("");
  const [rooms, setRooms] = useState([]);
  const [popUp, setPopUp] = useState("");
  const [roomId, setRoomId] = useState("");
  const [roomNumber, setRoomNumber] = useState("");

  const handleFilteredHotels = () => {
    const res = getFilteredHotels(hotels, search);
    setFilteredHotels(res);
  };

  useEffect(() => {
    handleFilteredHotels();
  }, [search, hotels]);

  const handleSelectedHotel = (id) => {
    setSelectedHotel(id);
    setRooms(hotelsMap[id].roomDto);
    setPopUp("1");
  };

  const handleClose = () => {
    if (popUp == "1") {
      setPopUp("");
      setSelectedHotel("");
    }
    if (popUp == "2") {
      setPopUp("1");
    }
  };

  const setRoomDetails = (id, number) => {
    setRoomNumber(number);
    setRoomId(id);
    setPopUp("2");
  };

  const handleDeleteRoom = async () => {
    try {
      const res = await deleteRoom(selectedHotel, roomNumber);
      toast.success(res);
      fetchHotels();
    } catch (error) {
      toast.error("Failed To Delete Room");
    } finally {
      setPopUp("");
      setSelectedHotel("");
      setRoomId("");
      setRoomNumber("");
    }
  };

  return (
    <div className="container">
      <ToastContainer />
      {selectedHotel && (
        <img
          className="backarrow"
          src={backarrow}
          alt=""
          onClick={handleClose}
        />
      )}
      <div className="room_header">
        <div className="room_header_text">Delete Room</div>
        <div className="search_bar">
          <input
            type="text"
            placeholder="Search....."
            onChange={(e) => setSearch(e.target.value)}
          />
          <img src={Search} alt="" width="15" />
        </div>
      </div>
      <div className="grid_items">
        {!selectedHotel &&
          popUp === "" &&
          filteredHotels.map((h) => (
            <div
              className="update_item"
              key={h.hotelId}
              onClick={() => handleSelectedHotel(h.hotelId)}
            >
              {h.hotelName}
            </div>
          ))}
        {selectedHotel &&
          popUp === "1" &&
          rooms.map((r) => (
            <div
              className="update_item"
              key={r.roomId}
              onClick={() => setRoomDetails(r.roomId, r.roomNumber)}
            >
              {r.roomNumber}
            </div>
          ))}
        {selectedHotel && popUp === "2" && (
          <div className="delete_popup room_delete_pop">
            <h2>Room Number :{roomNumber}</h2>
            <span>Are You Sure</span>
            <div className="button_group">
              <button onClick={handleDeleteRoom}>Yes</button>
              <button onClick={() => setPopUp("1")}>No</button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default DeleteRoomComponent;
