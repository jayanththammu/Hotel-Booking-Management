import React, { useEffect, useState } from 'react'
import AddHotel from './AddHotel'
import UpdateHotelPopUp from './UpdateHotelPopUp'
import {    updateHotel } from '../api/AdminDashboard';
import { useHotels } from '../hooks/useHotels';
import SearchBar from './SearchBar';
import HotelCard from './HotelCard';
const UpdateHotel = () => {
  const [load,setLoad] = useState(false);
   const [hotels,setHotels] = useState([]);
   const [selectedHotel,setSelectedHotel] = useState("");
   const [data,setData] = useState({});
   const [Loading,setIsLoading] = useState(false);
   const [err,setError] = useState(null);
   const [search,setSearch] = useState('');

   

  const selectedHotelDetails = hotels.find(h => h.id === selectedHotel);

  const handleSubmit =  async ()=>{

    const res = await updateHotel(selectedHotel,data[selectedHotel]);
    console.log(res);
  }


  const handleSelectedHotel = (hotelid)=>{

    if(!data[hotelid]){
      setData(prev =>({
        ...prev,
        [hotelid]:{
            description: "",
          rating: "",
          contactNo: "",
          email: "",
        }
      }));
    }
    setSelectedHotel(hotelid);
    console.log(hotelid);
  }
    

    useHotels(setHotels,setIsLoading,setError);

    const filteredHotels = hotels.filter(hotel =>{
      return hotel.hotelName.toLowerCase().includes(search.toLowerCase());
    });

  const handle = ()=>{
    setLoad(true);
  }
  return (
    <div>
      
       <header>
        <h2 className='fs-25 l-sm   text-center text-color-black'>Update Hotel</h2>
        <SearchBar setSearch={setSearch} search={search}></SearchBar>
      </header>
     
      <section className='hotel_container'>
        
        { 
          filteredHotels.map(hotel=>(
            <HotelCard hotelName={hotel.hotelName} id={hotel.id} handleSelectedHotel={handleSelectedHotel}/>
          ))
        }
        { selectedHotel && <UpdateHotelPopUp handleSubmit={handleSubmit} hotelName={selectedHotelDetails?.hotelName} hotelid = {selectedHotel} setSelectedHotel={setSelectedHotel} setLoad={setLoad} data={data} selectedHotel={selectedHotel} setData={setData} />}
      </section>
      
    </div>
  )
}

export default UpdateHotel