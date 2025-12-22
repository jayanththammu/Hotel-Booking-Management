import React, { useEffect, useRef, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import "../../styles/UserDashboard/HotelDetails.css";
import { getHotelDetails } from "../../api/UserServices";

import { useRoomCache } from "../../RoomCacheContext";

const HotelDetails = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  const cancelled = useRef(false);
  const { roomCache, setRoomsForHotel } = useRoomCache();
  const [rooms, setRooms] = useState([]);
  const [error, setError] = useState(null);

  const [isLoading, setIsLoading] = useState(true);
  const fetchRooms = async () => {
    try {
      const res = await getHotelDetails(id);
      if (!cancelled.current) {
        setRooms(res);
        setRoomsForHotel(id, res);
      }
    } catch (error) {
      if (!cancelled.current) setError(error);
    } finally {
      if (!cancelled.current) setIsLoading(false);
    }
  };

  useEffect(() => {
    cancelled.current = false;
    if (roomCache[id]) {
      setRooms(roomCache[id]);
      setIsLoading(false);
      return;
    }

    fetchRooms();

    return () => {
      cancelled.current = true;
    };
  }, [id, roomCache, setRoomsForHotel]);
  if (isLoading) {
    return (
      <div className="hotel_details_page">
        <button onClick={() => navigate(-1)}>Back</button>
        <br></br>
        <br></br>
        Loading Rooms...
      </div>
    );
  }
  if (error) {
    return (
      <div className="hotel_details_page">
        <button onClick={() => navigate(-1)}>Back</button>
        <br></br>
        <br></br>
        {error}
      </div>
    );
  }

  if (rooms.length == 0) {
    return (
      <div className="hotel_details_page">
        <button onClick={() => navigate(-1)}>Back</button>
        <br></br>
        <br></br>
        No Rooms
      </div>
    );
  }

  return (
    <div className="hotel_details_page">
      <button onClick={() => navigate(-1)}>Back</button>
      <br></br>
      <h2>Hotel Details</h2>
      <table className="table">
        <thead>
          <tr>
            <th>Room No</th>
            <th>Room Type</th>
            <th>Capacity</th>
            <th>Price</th>
            <th>Book</th>
          </tr>
        </thead>
        <tbody>
          {rooms.map((r) => (
            <tr key={r.roomId}>
              <td>{r.roomNumber}</td>
              <td>{r.roomType}</td>
              <td>{r.noOfShares} </td>
              <td>{r.roomPrice}</td>
              <td>
                <Link to={`/user/book/${id}/${r.roomId}/${r.roomPrice}`}>
                  <button>book</button>
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default HotelDetails;
// {roomId: 14, roomNumber: 1, noOfShares: 4, roomPrice: 500, roomType: 'NonAc'}
// length
// :
// 1
// [[Prototype]]
// :
// Array(0)
