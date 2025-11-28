import React, { useEffect, useState } from 'react'
import SearchBar from './SearchBar';
import { useHotels, useRoomCount } from '../hooks/useHotels';
import Loading from './Loading';
import { FilteredHotels } from '../hooks/FilteredHotels';
import HotelCard from './HotelCard';
import UpdateRoomPopup from './UpdateRoomPopup';
import { updateRoom } from '../api/AdminDashboard';

const UpdateRoom = () => {
 
  const [selectedHotel,setSelectedHotel] = useState('');
  const [hotels,setHotels] = useState([]);
  const [search,setSearch] = useState('');
  const [isLoading,setIsLoading] = useState(false);
  const [error,setError] = useState(null);
  const [roomsCount,setRoomsCount] = useState([]);
  const [data,setData] = useState({});
  const [loading,setLoading] = useState(false);

/*
 roomNo;
	private Integer roomShares;
	private String roomType;
	private Integer pricePerNight;
	private Boolean status;
*/
  useRoomCount(setRoomsCount);
  useHotels(setHotels,setError,setIsLoading);

 const filteredHotels = FilteredHotels(hotels,search);
  
  const handleSelectedHotel = (id)=>{

    setSelectedHotel(id);

    setData({
      ...data,
      roomNo:'',
      roomShares:'',
      roomType:'NonAc',
      pricePerNight:'',
      status:false});
   
  }

  const handleSubmit = async ()=>{

    try {
      setLoading(true);
    console.log(data);      
    const res = await updateRoom(selectedHotel,data.roomNo,data);
    alert(res);
    } catch (error) {
      console.log(error);
    }
    finally{

       setLoading(false);
    }

  }
  const handleData = (e)=>{

    const {name,value} = e.target;

    setData((prev)=>({
      ...prev,[name]:value
    }));


  }
  
  if(isLoading){
    return ( <Loading />)
  }
  if(error){
    return (
      <span>{error}</span>
    )
  }

  

  return (
    <div>
      <header className='text-center fs-25 l-sm '>Update Room</header>
      <SearchBar search={search} setSearch={setSearch}/>
      { loading && <Loading />}
      <section className='hotel_container'>
       
       { filteredHotels.map( hotel =>(
        <HotelCard id={hotel.id} hotelName={hotel.hotelName} handleSelectedHotel={handleSelectedHotel}/>
       ))}

       { selectedHotel && <UpdateRoomPopup  handleSubmit={handleSubmit} setSelectedHotel={setSelectedHotel} hotel={roomsCount.find(h => h.id == selectedHotel)} data={data} handleData={handleData}/>}
         

          
        
        
      </section>
       
    </div>
  )
}

export default UpdateRoom