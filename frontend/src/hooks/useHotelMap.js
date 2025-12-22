import { useMemo } from "react";

export function useHotelMap(hotels){

    const hotelMap = useMemo(()=>{
        return hotels.reduce((acc,hotel)=>
       { acc[hotel.hotelId] = hotel;
         return acc;},{})
    },[hotels]);

  

  
    return hotelMap;
}
export function useRoomMap(rooms){

    const roomMap = useMemo(()=>{
        return rooms.reduce((acc,room)=>{
            acc[room.roomId]=room; 
            return acc;
        },{})
    },[rooms]);
     
    
    return roomMap;
}