import React from 'react'

const UserHotelCard = ({hotel,handleview}) => {
  const name = hotel.hotelName;
  const description = hotel.description;
  const id = hotel.id;
  const rating = hotel.rating;
  const minPrice = hotel.minPrice;
  const roomTypes = hotel.roomTypes;
 
  return (
    <div className='user_hotel_card_container'>
     
        <h2 className=''>{name}</h2>
        <p>{description}</p>
        <p> <span>Room Types</span></p>
        <ul>
          {roomTypes.split(",").map((s,ind)=>(
           <li key={ind}>
            {s}
           </li> 
          ))}
          
        </ul>
        <p>starting at <span>₹{minPrice} / night</span></p>
        <p><span>Rating</span> : {rating}</p>

      <div className="btn_card">
      <button onClick={()=>{handleview(id)}}>Book Now</button>
      <button>View Details</button>  
      </div>
      
    </div>
  )
}

export default UserHotelCard