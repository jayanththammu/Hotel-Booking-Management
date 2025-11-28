import React, { useEffect, useState } from 'react'
import { useHotels } from '../hooks/useHotels';
import Loading from './Loading';
import DeletePopUp from './DeletePopUp';
import { deleteHotel } from '../api/AdminDashboard';
import { FilteredHotels } from '../hooks/FilteredHotels';
import HotelCard from './HotelCard';
import SearchBar from './SearchBar';

const DeleteHotels = () => {
  const [hotels,setHotels] = useState([]);
  const [Loading,setIsLoading] = useState(false);
  const [error,setError] = useState(null);
  const [selectedHotel,setSelectedHotel] = useState('');
  const [search,setSearch] = useState('');

  const filteredHotels = FilteredHotels(hotels,search);

 const reloadHotels = useHotels(setHotels,setIsLoading,setError);

  
  const handleDeleteHotel = async ()=>{

    const response  =  await deleteHotel(selectedHotel);
 
    reloadHotels();

   
  }
  

 

  const handleSelectedHotel = (id)=>{
    setSelectedHotel(id);
  }
 
  if(Loading){
    return (
      <Loading />
    )
  }

  if(error){
    return (
      <p>{error}</p>
    )
  }
  return (
    <div>
      <header className='text-center fs-25 l-s-'>Delete Hotel
        <SearchBar search={search} setSearch={setSearch} />
      </header>
      <section className='hotel_container'>
        {
          filteredHotels.map(hotel =>(
            <HotelCard id={hotel.id} hotelName={hotel.hotelName} handleSelectedHotel={handleSelectedHotel} />
          ))
        }
        
        { selectedHotel && <DeletePopUp handleDeleteHotel={handleDeleteHotel} setSelectedHotel={setSelectedHotel} id={selectedHotel} hotelName={ filteredHotels.find( hotel => hotel.id==selectedHotel)?.hotelName}/>}
      </section>
    </div>
  )
}

export default DeleteHotels