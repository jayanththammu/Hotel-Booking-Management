import adminApi from "./AdminAPi";

export async function addHotel(data){
   const response = await adminApi.post("/addhotel",data);

   return response.data;
}
export async function getHotels() {
   const res = await adminApi.get("/getallhotels");

   return res.data;
}

export async function findHotels() {
   const res = await adminApi.get("/getHotelData");

   return res.data;
}

export async function addHotelRoom(id,data) {
      const res = await adminApi.post(`/addRoom/${id}`,data);

      return res.data;
}

export async function updateHotel(id,data) {
   const res = await adminApi.put(`/updatehotel/${id}`,data);

   return res.data;
}

export async function deleteHotel(id) {
   const res = await adminApi.delete(`/deletehotel/${id}`);
   return res.data;
}

export async function getHotelRooms() {
   const res = await adminApi.get('/getroomcount');

   return res.data;
}
export async function updateRoom(id,roomNo,data) {
   const res = await adminApi.put(`/updateroom/${id}/${roomNo}`,data);

   return res.data;
}