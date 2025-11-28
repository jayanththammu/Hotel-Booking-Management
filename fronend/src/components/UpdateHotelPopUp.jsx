import React, { useState } from 'react'

const UpdateHotelPopUp = ( {handleSubmit,hotelName,setLoad,hotelid,setSelectedHotel,data,selectedHotel,setData}) => {
    const [closePopUP,setUpClose] = useState(false);

    const handleChange = (e)=>{
      let {name,value} = e.target;

      setData(prev=>({
        ...prev,
        [selectedHotel]:{
          ...prev[selectedHotel],
          [name]:value
        }
      }));

      console.log(data);
    }
   
    const handleClose = ()=>{
        setUpClose(true)
        setSelectedHotel('');
                      

        setTimeout(() =>
              setLoad(false)
        ,500);

    }

  return (
    <div className={closePopUP ? 'popup close-popup' : 'popup'}> 
    <button className='close-btn' onClick={handleClose}> &times;</button>
     
        <div className="popup-container oy-s">
        <div className="popup-content">
            <div className="container-md bg-white">
           <header className='text-color-black fs-20  '>Hotel Details:</header>
           <section>
            <p> <b>Name</b> : {hotelName} {hotelid}</p>
            <p> <b>Address</b> : 33/c addaguta road JNTU</p>
            <p></p>
           </section>

        </div>
        <section className='m-t-4 p-4 text-color-black'>
            <form action="">

            <label htmlFor="" className='text-color-black label'>Description:</label>
           <textarea name="description" id="" value={data[selectedHotel].description} placeholder='type here.......' className='form-input' style={{resize:'none'}} onChange={handleChange}></textarea>
 
            
            <label htmlFor=""  className='text-color-black label'>Rating:</label>
            <input type="text" name='rating' value={data[selectedHotel].rating} className='form-input' placeholder='rating' onChange={handleChange}/>

            <label htmlFor="" className='text-color-black label'>Email:</label>
            <input type="email"  name='email' value={data[selectedHotel].email} className='form-input' placeholder='Email' onChange={handleChange}/>

            <label htmlFor="" className='text-color-black label'>Mobile:</label>
            <input type="tel" name='contactNo' value={data[selectedHotel].contactNo} className='form-input' placeholder='Mobile' onChange={handleChange}  />
            <button type='submit' onClick={(e)=>{
              e.preventDefault();
              handleSubmit();
            }}>submit</button>

            </form>
        </section>
        </div>
        </div>
    </div>
  )
}

export default UpdateHotelPopUp
 
	// private String rating;
	// private String email;
	// private String contactNo;