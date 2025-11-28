import React, { useState } from 'react'

const AddRoom = ({index,roomData,handleRoom,className}) => {

 

  const handleChange = (e) => {
    handleRoom(e, index);
  };
  return (
    <div className={` p-4 m-t-4 bg-dg rounded ${className}`}>
       <div className="form-input-group ">
             <label htmlFor="roomNo" className='label w-2 m-t-4'>Room No :</label>
             <input type="number" id='roomNo' className='form-input w-50' name='roomNo' value={roomData?.roomNo || ''} onChange={handleChange}/>
          </div>

          <div className="form-input-group"> 
               <label htmlFor="roomShares" className='label w-2'>room Shares :</label>
               <input type="number" id='roomShares' className='form-input w-50' name='roomShares' value={roomData?.roomShares || ''} onChange={handleChange}/>
          </div>

          <div className="form-input-group" name="roomType" > 
               <label htmlFor="roomType" className='label w-2'>room Type :</label>
               <select name="roomType" id="roomType" className='form-input' value={roomData?.roomType || 'NonAc'} onChange={handleChange}>
                <option value="Ac">Ac</option>
                <option value="NonAc">Non-Ac</option>
                <option value="NonAc">Non-Ac</option>
                <option value="deluxe">Deluxe Room</option>
                <option value="suite">Suite</option>

               </select>
          </div>

          <div className="form-input-group"> 
               <label htmlFor="pricePernight" className='label w-2'>Price Per Night :</label>
               <input type="number" id='pricePernight' className='form-input w-50'name='pricePerNight' onChange={handleChange} value={roomData?.pricePerNight || ''}/>
          </div>

         

            <div className="form-input-group"   > 
               <label htmlFor="status" className='label w-2'>Status :</label>
                <select name="status" id="status" className='form-input' onChange={handleChange} value={roomData?.status || 'false'}>
                <option value="true">Booked</option>
                <option value="false">Available</option>
               </select>
          </div>
    </div>
  )
}

export default AddRoom

/*
 
	private Boolean status;
	
*/