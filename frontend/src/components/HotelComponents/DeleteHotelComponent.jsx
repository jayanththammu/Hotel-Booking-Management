import React, { useState } from 'react'
import { deleteHotel } from '../../api/AdminServices';
import backarrow from '../../assets/backarrow.svg';
import { toast, ToastContainer } from 'react-toastify';

const DeleteHotelComponent = ({hotelsMap,fetchHotels,hotels}) => {
    const [selectedHotel,setSelectHotel] = useState('');
    const [showDeletePop,setShowDeletePop] = useState(false);
    const [hotelDetails,setHotelDetails] = useState({});

    
    const handleDeleteHotel = (id)=>{
    
        setSelectHotel(id);
        setShowDeletePop(true);
    
        const selected = hotelsMap[id];
        setHotelDetails(selected);
    }
    const closeDeleteHotel = ()=>{
      setSelectHotel('');
      setShowDeletePop(false);
    }
    
    const handleDelete =async ()=>{
      try {
        const res = await deleteHotel(selectedHotel);
        toast.success(res);
          fetchHotels();
      } catch (error) {
        toast.error("Failed To Delete");
      }finally{
        closeDeleteHotel();
      
      }
    }
  return (
    <div className='container delete '>
      <ToastContainer />
             {showDeletePop && <img className='backarrow' src={backarrow} alt="" onClick={closeDeleteHotel} />}
   
             <div className="card_title ">Delete</div>
             <div className={!showDeletePop ? 'grid_items' :'delete_popup_container'}>
               { !showDeletePop &&hotels.map(h => (
                   <div className='update_item' key={h.hotelId} onClick={()=>handleDeleteHotel(h.hotelId)} >
                    {h.hotelName} 
                   </div>))}
               {
                 showDeletePop && <div className='delete_popup'>
                     <h2>{hotelDetails.hotelName}</h2>
                     <span>Are You Sure</span>
                       <div className="button_group">
                         <button onClick={handleDelete}>Yes</button>
                         <button onClick={closeDeleteHotel}>No</button>
                       </div>
                     
                 </div>
               }
             </div>
         </div>
  )
}

export default DeleteHotelComponent