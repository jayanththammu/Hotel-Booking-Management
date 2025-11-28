import React, {   useEffect, useState } from 'react'
import { useHotels } from '../hooks/useHotels';
import Loading from './Loading';
import HotelCard from './HotelCard';
import SearchBar from './SearchBar';
 
const ViewHotels = () => {

  const [hotels,setHotels]=useState([]);
  const [error,setError] = useState(null);
  const [isLoading,setIsLoading] = useState(false);
  const [search,setSearch] = useState('');
 useHotels(setHotels,setError,setIsLoading);
  useEffect(()=>{
    const loadHotels = ()=>{
      setHotels([{id:'1',hotelName:'sri Sai Srinivasa'},{
        id:'2',
        hotelName:'HomeTel'
      }]);
    }

    loadHotels();
  } ,[])

  const filteredHotels =  hotels.filter(hotel =>
      hotel.hotelName.toLowerCase().includes(search.toLowerCase()));

  if(isLoading){
    return ( 
      <Loading />
    )
  }

  if(error){
    return (
      <span>{error}</span>
    )
  }

  return (
    <div>
      <header>
        <h2 className='text-center'>Hotels</h2>
        <SearchBar search={search} setSearch={setSearch}></SearchBar>
      </header>
      <main>
        <section className='hotel_container'>
          { filteredHotels.map( hotel =>(
         
            <HotelCard id={hotel.id} hotelName={hotel.hotelName}/>
          ))}
          
             
             
           
        </section>
      </main>
    </div>
  )
}

export default ViewHotels
 