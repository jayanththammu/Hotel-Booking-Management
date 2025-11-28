import React from 'react'

const HotelCard = ({id,hotelName,handleSelectedHotel}) => {
  return (
    <div className='hotel_card' onClick={()=>handleSelectedHotel(id)} key={id}>
        <div className="hotel_content">
            <span >{hotelName}</span>
        
        </div>

    </div>
  )
}

export default HotelCard