import React, { useEffect, useState } from 'react'
import Loading from './Loading';
import { addHotelRoom, findHotels } from '../api/AdminDashboard';
import AddRoom from './AddRoom';
import { useHotels } from '../hooks/useHotels';
import SearchBar from './SearchBar';

const HotelAddRoom = () => {
    const [hotels,setHotels] = useState([]);
    const [isLoading,setIsLoading] = useState(false);
    const [err,setError] = useState(null);
    const [search,setSearch] = useState("");
    const [selectedHotel,setSelectedHotel] = useState("");
    const [addingRoom,setAddingRoom] = useState(false);
     const [selected,SetSelected] = useState(false);

    const [roomData,setRoomData] = useState({}); 

    const handleRoom = (e,index)=>{
      const {name,value} = e.target;
      setRoomData(prev =>({
        ...prev
        ,[index]:{
          ...(prev[index] ?? {}),
          [name]:value,
        },}));
      
    }
    const handleSelectedHotel = (id)=>{
  
    setSelectedHotel(prev => (prev === id ? null : id));

    setRoomData(prev =>({
      ...prev,
      [id]:prev[id] ||{
         roomNo: "",
        roomShares: "",
      roomType: "NonAc",
      pricePerNight: "",
      status: "false",
      }
    }))
      
    }

    const handleAddRoom =  async ()=>{

      try{
        setAddingRoom(true);
        console.log(roomData[selectedHotel]);
        const data   = await addHotelRoom(selectedHotel,roomData[selectedHotel]);
        setError(null);
          setRoomData(prev => ({
      ...prev,
      [selectedHotel]: {}
    }));

        
        alert(data);
      }
      catch(err){
        setError(err);
      }
      finally{
        setAddingRoom(false);
      }

    }

    //Use Effect to Load Hotels
    useHotels(setHotels,setError,setIsLoading);

    if (isLoading) {
        return <Loading />;
    }

    if (err) {
        return <div>Error: {err}</div>;
    }
     
    const filteredHotels = hotels.filter((hotel)=>
      hotel.hotelName.toLowerCase().includes(search.toLowerCase())
    );
    
  return (
    <div className='p-4'> 
        <header>
            <SearchBar setSearch={setSearch} search={search}></SearchBar>
        </header>
      
      
        <section className='col'>
           <ul className='ul'>
            
        { filteredHotels.length > 0 ? filteredHotels.map((hotel,index)=>    
          ( <React.Fragment key={index}>
               <li className='li'   onClick={() =>handleSelectedHotel(hotel.id)}>{hotel.hotelName}</li>
               {selectedHotel === hotel.id && <div> 
            <AddRoom className='m-b-4' roomData={roomData[selectedHotel]} handleRoom={handleRoom} index={selectedHotel}/>
            <button className='btn btn-primary m-b-4' onClick={handleAddRoom}>Add</button>
               </div>  }
             
          </React.Fragment>
        ))  
          
        : "No Hotels Found" } 
               
           </ul>

           {/* {selected && <AddRoom />} */}
        </section>
       
    </div>
  )
}

export default HotelAddRoom 
