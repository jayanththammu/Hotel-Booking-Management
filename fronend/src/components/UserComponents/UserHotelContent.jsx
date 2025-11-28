import React, { useEffect, useState } from 'react'
import UserHotelCard from './UserHotelCard'
import userApi from '../../api/UserApi';
import { bookingDetails, userGetHotels } from '../../api/UserDashboard';
 
import UserHotelDetailsPopUP from './UserHotelDetailsPopUP';
 

const UserHotelContent = () => {
  const [hotelCardData,setHotelCardData] = useState([]);
  const [showDetails,setShowDetails] = useState(false);
  const [bookDetail,setBookDetails] = useState([]);
  const [selectedHotel,setSelectedHotel] = useState('');
  

  useEffect(()=> {
    const loadHotels =async ()=>{
      const res =await userGetHotels();
      setHotelCardData(res);
    }

    const loadBookingDetails = async ()=>{
      const res = await bookingDetails();
      setBookDetails(res);
    }

    loadHotels();
    loadBookingDetails();
  },[]);

  const handleview = (id)=>{
    
     setSelectedHotel(id);
    setShowDetails(true);
   
    }
    const handleClose = ()=>{
     
      setTimeout(()=>{
         setSelectedHotel('');
        setShowDetails(false)},500);
    }
  return (
    <div className=  'user_hotel_content' >
      {
         hotelCardData.map((hotel,index) =>(
          <UserHotelCard key={hotel.id} hotel={hotel} handleview={handleview}/>
        ))
      }
      {showDetails && <UserHotelDetailsPopUP handleClose={handleClose} bookingDetails={bookDetail.find(h => h.id === selectedHotel)} />}
       
        
      

    </div>
  )
}

export default UserHotelContent