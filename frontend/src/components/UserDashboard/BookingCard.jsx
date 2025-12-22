import React from "react";

const BookingCard = ({ booking }) => {
  console.log(booking);
  return (
    <div className="booking_card">
      <div className="booking_card_hotel_name">{booking.hotelName}</div>
      <div className="booking_card_RoomNo">Room No: {booking.roomNumber}</div>
      <div className="booking_card_checkIN">check In: {booking.checkIn}</div>
      <div className="booking_card_checkout">check Out: {booking.checkOut}</div>
      <div className="booking_card_amount">
        Amount : <span>{booking.amount}₹</span>
      </div>
    </div>
  );
};

export default BookingCard;
