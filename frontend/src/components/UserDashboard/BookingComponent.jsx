import React, { useEffect, useState } from "react";
import "../../styles/UserDashboard/Booking.css";
import { useNavigate, useParams } from "react-router-dom";
import { bookHotel } from "../../api/UserServices";
import { toast, ToastContainer } from "react-toastify";

const BookingComponent = () => {
  const [checkInDate, setCheckInDate] = useState();
  const [checkOutDate, setCheckOutDate] = useState();
  const navigate = useNavigate();
  const { hotelId, roomId, roomPrice } = useParams();
  const [price, setPrice] = useState(0);
  const userId = JSON.parse(localStorage.getItem("userCredentials")).id;

  const handleSubmit = async (e) => {
    e.preventDefault();
    const bookingData = {
      userId,
      hotelId,
      roomId,
      checkInDate,
      checkOutDate,
      amount: price,
    };

    try {
      const res = await bookHotel(bookingData);

      toast.success(res);
    } catch (error) {
      toast.error(error);
    }
  };

  useEffect(() => {
    if (!checkInDate || !checkOutDate) {
      setPrice(0);
      return;
    }
    const fetchPrice = () => {
      setPrice(calcPrice(Number(roomPrice), checkInDate, checkOutDate));
    };

    fetchPrice();
  }, [checkInDate, checkOutDate]);
  return (
    <div className="booking">
      <ToastContainer />
      <button
        onClick={() => {
          navigate(-1);
        }}
      >
        back
      </button>

      <center>
        <h2>Booking</h2>
        <form onSubmit={handleSubmit}>
          <div className="item">
            <label htmlFor="From">From</label>
            <input
              type="date"
              name="checkInDate"
              onChange={(e) => setCheckInDate(e.target.value)}
            />
          </div>
          <div className="item">
            <label htmlFor="From">To</label>
            <input
              type="date"
              name="checkOutDate"
              onChange={(e) => setCheckOutDate(e.target.value)}
            />
          </div>
          <div className="price">
            price: <span>{price}₹</span>
          </div>
          <br />
          <input type="submit" value="Book" />
        </form>
      </center>
    </div>
  );
};

export default BookingComponent;
// private Long userId;
// 	private Long hotelId;
// 	private Long roomId;
// 	private LocalDate checkInDate;
// 	private LocalDate checkOutDate;
// 	private Double amount;

function calcPrice(price, checkInDate, checkOutDate) {
  const checkIn = new Date(checkInDate);
  const checkOut = new Date(checkOutDate);
  if (checkIn > checkOut) {
    return;
  }
  const days = (checkOut - checkIn) / (1000 * 60 * 60 * 24);

  return price * days;
}
