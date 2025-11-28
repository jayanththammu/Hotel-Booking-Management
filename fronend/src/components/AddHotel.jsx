import React, { useState } from 'react'
import '../styles/AddHotel.css'
import AddRoom from './AddRoom'
import Loading from './Loading';
import { addHotel } from '../api/AdminDashboard';
const AddHotel = () => {
  const[isLoading,setIsLoading] = useState(false);
  const[data,setData] = useState({});
  const[rooms,setRooms] = useState([]);
  

  const handleRoom = (e,index)=>{
    const {name,value} = e.target;

    const updatedRooms = rooms.map((room,i)=>{
      if(i == index){
        return {...room,[name]:value};
      }
      return room;
    });
    setRooms(updatedRooms);
    console.log(updatedRooms);
 
  }

  const addRoomComponent = ()=>{
    setRooms([
      ...rooms,
      {
        roomNo: '',
        roomShares: '',
        roomType: 'Ac', // Default value
        pricePerNight: '',
        status: 'false',
      }
    ])
  }

  const handle = (e)=>{
    const{name,value} = e.target;

    setData({...data,[name]:value});
    console.log(value);
    console.log(data);
  }

  const handleSave = async ()=>{
    try{
      setIsLoading(true);
      const finalHotelData = {
        ...data,rooms:rooms,
      }
      const response = await addHotel(finalHotelData);

      console.log(response);
    }catch(err){

    }finally{
      setIsLoading(false);

    }
  }
  
  return (
    <div className='bg_b rounded h-100 oy-s'>
      <header className='text-center fs-25 l-sm ts-0 p-4 '>Add Hotel</header>
       {isLoading && <Loading />}
        <div  className='m-t-4 p-4 '>

          <div className="form-input-group ">
             <label htmlFor="hotelName" className='label w-2'>Hotel Name :</label>
             <input type="text" id='hotelName' className='form-input w-50' name='hotelName' onChange={handle}/>
          </div>

          <div className="form-input-group"> 
               <label htmlFor="description" className='label w-2'>description :</label>
               <input type="text" id='description' className='form-input w-50'name='description' onChange={handle}/>
          </div>

          <div className="form-input-group"> 
                <label htmlFor="location" className='label w-2'>location :</label>
                <input type="text" id='location' className='form-input w-50' name='location' onChange={handle} />
          </div>

          <div className="form-input-group"> 
                  <label htmlFor="rating" className='label w-2'>rating :</label>
                  <input type="text" id='rating' className='form-input w-50' name='rating' onChange={handle}/>
          </div>

          <div className="form-input-group"> 
              <label htmlFor="email" className='label w-2'>email :</label>
               <input type="email" id='email' className='form-input w-50' name='email' onChange={handle}/>
          </div>

          <div className="form-input-group"> 
              <label htmlFor="contactNo" className='label w-2'>contact no :</label>
              <input type="tel" id='contactNo' className='form-input w-50' name='contactNo' onChange={handle}/>
          </div>
        {
          rooms.map( (room,index)=>(
            <AddRoom 
            key = {index}
            index = {index}
            roomData = {room}
            handleRoom={handleRoom}/>
          ))
        }
         <button className='btn-add m-t-4' onClick={addRoomComponent}>Add Room</button>


         

          <button className='b-s m-t-4' onClick={handleSave}>Save</button>
        </div>
       
    </div>
  )
}

export default AddHotel
 