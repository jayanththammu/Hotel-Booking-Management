import React, { useState } from 'react'
import AddRoom from '../components/AddRoom'
import AddHotel from '../components/AddHotel'
import DeleteHotels from '../components/DeleteHotels'
import UpdateHotel from '../components/UpdateHotel'
import UpdateRoom from '../components/UpdateRoom'
import ViewHotels from '../components/ViewHotels'
import HotelAddRoom from '../components/HotelAddRoom'


 



const AdminDashbard = () => {

    const[ addHotel,setAddHotel] = useState(true);
    const [addRoom,setAddRoom] = useState(false);
    const [updateRoom,setUpdateRoom] = useState(false);
    const [updateHotel,setUpdateHotel] = useState(false);
    const [viewHotel,setViewHotel] = useState(false);
    const [deleteHotel,setDeleteHotel] = useState(false);

    const resetAll = ()=>{
        setAddHotel(false);
        setAddRoom(false);
        setUpdateRoom(false);
        setUpdateHotel(false);
        setViewHotel(false);
        setDeleteHotel(false);
    }

  return (
    <div className='body m-0    '> 
        <h3 className='block m-0 p-4 text-center  t-w t-sh l-sm fs-25 bg-color'>Admin Dashboard</h3>
        <div className="flex">
            <div className="left">
            <ul>
              <li className=''> <button className='b-b first' onClick={()=>{resetAll(); setAddHotel(true);}} >add Hotel</button></li>
              <li> <button className='b-b' onClick={()=>{resetAll(); setAddRoom(true);}} >Add Room</button> </li> 
              <li> <button className='b-b' onClick={()=>{resetAll(); setUpdateHotel(true);}}>update Hotel</button> </li>  
              <li> <button className='b-b' onClick={()=>{resetAll(); setUpdateRoom(true);}}>update Room</button> </li>
              <li> <button className='b-b' onClick={()=>{resetAll(); setViewHotel(true);}}>View Hotels</button></li> 
              <li> <button className='b-b'  onClick={()=>{resetAll(); setDeleteHotel(true);}}>Delete Hotel</button></li> 
               
            </ul>
 
        </div>
        <div className="right-container ">
            { addHotel && <AddHotel /> }
            { addRoom && <HotelAddRoom />}
            { updateHotel && <UpdateHotel />}
            { updateRoom && <UpdateRoom /> }
            { deleteHotel && <DeleteHotels /> }
            { viewHotel && <ViewHotels />}
        </div>
        </div>
       
     
 
    </div>
  )
}

export default AdminDashbard