import React, { useEffect, useState } from "react";
import Search from "../../assets/subtract.svg";
import { getFilteredHotels } from "../../pages/AdminDashboard";
import backarrow from "../../assets/backarrow.svg";
import "../../styles/UpdateAdminComponet.css";
import { useRoomMap } from "../../hooks/useHotelMap";
import { updateRoom } from "../../api/AdminServices";
import { toast, ToastContainer } from "react-toastify";
const UpdateRoomComponent = ({ hotels, hotelsMap, fetchHotels }) => {
  const [filteredHotels, setFilteredHotels] = useState(hotels);
  const [search, setSearch] = useState("");
  const [selectedHotel, setSelectedHotel] = useState("");
  const [popUp, setPopUp] = useState("");
  const [rooms, setRooms] = useState([]);
  const [roomDetails, setRoomDetails] = useState({});
  const [selectedRoom, setSelectedRoom] = useState("");

  const roomMap = useRoomMap(rooms);
  useEffect(() => {
    handleFilteredHotels();
  }, [search, hotels]);

  const handleFilteredHotels = () => {
    const res = getFilteredHotels(hotels, search);
    setFilteredHotels(res);
  };

  const handleSelectedHotel = (id) => {
    setSelectedHotel(id);
    setPopUp("1");

    setRooms(hotelsMap[id].roomDto);
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

  const handleUpdateRoom = (id) => {
    setRoomDetails(roomMap[id]);
    setSelectedRoom(id);
    setPopUp("2");
  };

  const handleOnChange = (e) => {
    const { name, value } = e.target;

    setRoomDetails({ ...roomDetails, [name]: value });
  };

  const saveRoom = async () => {
    try {
      const res = await updateRoom(selectedHotel, selectedRoom, roomDetails);
      toast.success(res);
      fetchHotels();
    } catch (error) {
      toast.error("Failed To Update Room");
    } finally {
      setRoomDetails({});
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
        <div className="room_header_text">Update Room</div>
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
              onClick={() => handleUpdateRoom(r.roomId)}
            >
              {r.roomNumber}
            </div>
          ))}
        {selectedHotel && popUp === "2" && (
          <div className="update_room_form">
            <form action={saveRoom}>
              <input
                type="number"
                placeholder="Room Number"
                name="roomNumber"
                value={roomDetails.roomId}
                onChange={(e) => handleOnChange(e)}
                disabled
                required
              />

              <select
                name="noOfShares"
                value={roomDetails.noOfShares}
                onChange={(e) => handleOnChange(e)}
                required
              >
                <option value="">Shares</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select>

              <select
                name="roomType"
                value={roomDetails.roomType}
                onChange={(e) => handleOnChange(e)}
                required
              >
                <option value="">Type</option>
                <option value="Ac">Ac</option>
                <option value="NonAc">Non-Ac</option>
                <option value="Delux">Delux</option>
                <option value="Suite">Suite</option>
              </select>
              <input
                type="number"
                placeholder="price"
                name="roomPrice"
                value={roomDetails.roomPrice}
                onChange={(e) => handleOnChange(e)}
                required
              />
              <input type="submit" />
            </form>
          </div>
        )}
      </div>
    </div>
  );
};

export default UpdateRoomComponent;
