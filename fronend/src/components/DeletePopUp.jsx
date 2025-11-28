import React, { useState } from 'react'

const DeletePopUp = ({id,hotelName,setSelectedHotel,handleDeleteHotel}) => {
  const [close,setClose] = useState(false);
  

  const handleYes =async ()=>{

   
    setClose(true);
      setTimeout(async () => {
      await handleDeleteHotel();  // delete + reload data
      setSelectedHotel('');        // finally close popup
    }, 2000); 


  }

  const handleClose = ()=>{
    setClose(true);
    setTimeout(()=>{
      setSelectedHotel();
    } ,2000);

  }
  return (
    <div className={close ? 'popup-sm popup-close' : 'popup-sm'}>
        
        <header className='text-center fs-25 l-s-sm text-color-white text-shadow-small'>Confirm Delete </header>
        <section className='p-4'>
            <p> <b>Hotel Name</b> : {hotelName} {id}</p>
            <section>
                <button className='btn rounded btn-primary ' onClick={handleYes}>yes</button>
            <button className='btn rounded ' onClick={handleClose}>NO</button>
            </section>
        </section>
    </div>
  )
}

export default DeletePopUp