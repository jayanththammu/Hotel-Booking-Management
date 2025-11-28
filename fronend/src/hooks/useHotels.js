import { useEffect } from "react";
import {  findHotels, getHotelRooms } from '../api/AdminDashboard';
import adminApi from "../api/AdminAPi";


export function useHotels(setHotels,setError,setIsLoading){

     const load = async () =>{
                try{
                    setIsLoading(true);
                    const data = await findHotels();
                    setHotels(data);
                    setError(null);
                  
                }catch(err){
                    setError(err.message || "An Error Occured");
                    
                }finally{
                    setIsLoading(false);
                }
    
            };
           
    
     useEffect(()=>{
     
         load();
           
        },[]);

        return load;
    
}

export async function useRoomCount(setRoomsCount){
   

    useEffect( ()=>{
         const loadRoomCount = async ()=>{
        const res = await getHotelRooms();
        setRoomsCount(res);
        console.log(res);
    };
        loadRoomCount();
    },[setRoomsCount]);
    
}