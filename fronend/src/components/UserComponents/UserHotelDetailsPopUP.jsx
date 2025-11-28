import React, { useEffect, useState } from 'react'
import { bookHotel } from '../../api/UserDashboard';
 
const UserHotelDetailsPopUP = ({bookingDetails,handleClose}) => {
    const {id,hotelName,roomDetails,roomPriceShares} = bookingDetails;
    const [startDate,setStartDate] = useState('');
    const [endDate,setEndDate] = useState('');
    const [roomType,setRoomType] = useState("");
    const [roomShares,setRoomShares] = useState('');
    const [price,setPrice]= useState('');

    const shareData = parseRomPriceShares(bookingDetails.roomPriceShares);
    useEffect(()=>{
      if(!roomType){
        setRoomShares('');
        setPrice('');
        // setStartDate('');
        // setEndDate('');
        return;

      }

      const option = shareData[roomType];

      if(option?.length>0 && !roomShares){
        setRoomShares(option[0].shares);
        return;
      } 
      if(!startDate || !endDate || !roomShares){
        return;
      }
      const handle = ()=>{
       
    
        const selected = shareData[roomType].find(
        item => item.shares === Number(roomShares)
    );
          setPrice(calculatePrice(startDate,endDate,selected.price));
    }
    handle();

    },[roomType, startDate,roomShares, endDate]);

    const bookingHotel = async ()=>{
      const data = {"hotelId":id,roomType,"startDate":new Date(startDate),"endDate":new Date(endDate)};
      const res = await bookHotel(data); 
      console.log(res);
    };
   //private Long hotelId;
	// private String roomType;
	// private LocalDate startdate;
	// private LocalDate endDate;
    
  return (
   
        <div className="show_details_popup">
          <button className='close' onClick={handleClose}>&times;</button>
                      <h2>{hotelName}</h2>
            <span>Room Types:</span>
            <ul>
                {roomDetails.split(",").map(s =>(
                    <li>{s}</li>
                ))}
                 
            </ul>        
           <div className="booking_section">
            <label htmlFor="roomType">Room Type</label>
            <select name="roomType" id="roomType" value={roomType} onChange={e => setRoomType(e.target.value)} >
                <option value="">select</option>
                {roomDetails.split(",").map(s =>(
                    <option value={s}>{s}</option>
                ))}
            </select>
            <label htmlFor="roomShares">Shares</label>
            <select name="roomShares" id="roomShares" value={roomShares} onChange={e=>setRoomShares(e.target.value)} disabled={!roomType}>
                {roomType && 
                 
                shareData[roomType]?.map((obj, idx) => (
        <option key={idx} value={obj.shares} >
          {obj.shares}    
        </option>
        
      ))}
                 
            </select>
            <label htmlFor="start">from</label>
            <input type="date" id='start'  value={startDate} onChange={e=> setStartDate(e.target.value)}/>
            <label htmlFor="end">To</label>
            <input type="date" id='end' value={endDate} onChange={e=> setEndDate(e.target.value)}/>
            <input type="text" value={price} placeholder='Total Amount'readOnly />
            <button onClick={bookingHotel}><div className='load'></div> Book Now</button>
           </div>
        </div>
  
  )
}
function parseRomPriceShares(roomPriceShares){
    const res = {};
    roomPriceShares.trim().split(",").forEach(r=>{
       const parts = r.trim().split(" ");
      
    const roomType = parts[0];         // AC or Non-AC
    const shares = Number(parts[1]);   // after "shares :"
    const price = Number(parts[2]); 

    if (!res[roomType]) {
      res[roomType] = [];
    }

    res[roomType].push({ shares, price });
  });
  console.log(res);
  return res;

  
}
 function calculatePrice(startDate, endDate, price) {
  if (!startDate || !endDate) {
    console.log("Dates not selected");
    return 0;
  }

  const sDate = new Date(startDate);
  const eDate = new Date(endDate);

  const diffMs = eDate - sDate; // in ms
  const diffDays = diffMs / (1000 * 60 * 60 * 24); // convert to days

  if (isNaN(diffDays) || diffDays <= 0) {
    console.log("Invalid date range");
    return 0;
  }

  const amount = diffDays * price;
  console.log("Days:", diffDays, "Amount:", amount);
  return amount;
}

export default UserHotelDetailsPopUP