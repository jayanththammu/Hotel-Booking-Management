import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import BookingCard from "./BookingCard";
import { getBookings } from "../../api/UserServices";

const Bookings = () => {
  const { userId } = useParams();

  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      const res = await getBookings(userId);
      setBookings(res);
    } catch (err) {}
  };

  const navigate = useNavigate();
  return (
    <div className="booking_container">
      <button
        onClick={() => {
          navigate(-1);
        }}
      >
        back
      </button>

      {bookings.map((b) => (
        <BookingCard booking={b} />
      ))}
    </div>
  );
};

export default Bookings;
