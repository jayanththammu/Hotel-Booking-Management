import React from 'react'
import AddRoom from './AddRoom.jsx'
//rooms: 2, hotelName: 'Taj Palace', roomNos: '102,101'
const UpdateRoomPopup = ({hotel,setSelectedHotel,handleData,data,handleSubmit}) => {
  const rooms = hotel.id;
  const hotelName = hotel.hotelName;
  const roomNos = hotel.roomNos;
  return (
    <div className='update_room_popup'> 
         <header>
           <h2 className='text-center fs-20 text-color-white l-sm'>update Room</h2> 
           <button className='room_popup_close' onClick={()=> setSelectedHotel('')}>&times;</button>
         </header>
         <main>
            <section className='room_details'>
                <p>Hotel Name: <span>{hotelName}</span> </p>
                <p>Rooms Available : <span>{rooms}</span></p>
                <p>Room No's : <span>{roomNos}</span></p>
           </section>

           <section className='room_update'>
              

                <div className="form-input-group ">
                  <label htmlFor="roomNo" className='label w-2 m-t-4'>Room No :</label>
                  <input type="number"  className='form-input w-50' required  name='roomNo' value={data.roomNo} onChange={(e)=>handleData(e)}/>
                </div>

                <div className="form-input-group"> 
               <label htmlFor="roomShares" className='label w-2'>room Shares :</label>
               <input type="number" id='roomShares' className='form-input w-50' name='roomShares' value={data.roomShares} onChange={(e)=>handleData(e)} />
          </div>

          <div className="form-input-group" name="roomType" > 
               <label htmlFor="roomType" className='label w-2'>room Type :</label>
               <select name="roomType" id="roomType" className='form-input' value={data.roomType} onChange={(e)=>handleData(e)}>
                <option value="Ac">Ac</option>
                <option value="NonAc">Non-Ac</option>
                <option value="deluxe">Deluxe</option>
                <option value="suite">Suite</option>
               </select>
          </div>

          <div className="form-input-group"> 
               <label htmlFor="pricePernight" className='label w-2'>Price Per Night :</label>
               <input type="number" id='pricePernight' className='form-input w-50'name='pricePerNight'  value={data.pricePerNight}  onChange={(e)=>handleData(e)} />
          </div>

         

            <div className="form-input-group"   > 
               <label htmlFor="status" className='label w-2'>Status :</label>
                <select name="status" id="status" className='form-input' value={data.status}  onChange={(e)=>handleData(e)}  >
                <option value="true">Booked</option>
                <option value="false">Available</option>
               </select>
          </div>


          <button className='btn btn-primary ' onClick={handleSubmit}>Update</button>

           </section>
         </main>
    </div>
  )
}

export default UpdateRoomPopup