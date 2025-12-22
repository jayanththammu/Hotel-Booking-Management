import axios from "axios";
import adminApi from "./AdminAPi";
import { data } from "react-router-dom";

export async function addHotel(data) {
  const res = await adminApi.post("/addhotel", data);
  return res.data;
}
export async function getHotelsData() {
  const res = await adminApi.get("gethotels");
  return res.data;
}
export async function updateHotel(data, id) {
  const res = await adminApi.put(`/updatehotel/${id}`, data);
  return res.data;
}
export async function deleteHotel(id) {
  const res = await adminApi.delete(`deletehotel/${id}`);

  return res.data;
}

export async function addRoom(data, id) {
  const res = await adminApi.post(`/addroom/${id}`, data);
  return res.data;
}

export async function getRooms(hotelid) {
  const res = await adminApi.get(`/getrooms/${hotelid}`);

  return res.data;
}

export async function updateRoom(id, roomid, data) {
  const res = await adminApi.put(`/updateroom/${id}/${roomid}`, data);
  return res.data;
}

export async function deleteRoom(hotelid, roomno) {
  const res = await adminApi.delete(`/delroom/${hotelid}/${roomno}`);
  return res.data;
}
