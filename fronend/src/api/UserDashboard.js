import { data } from "react-router-dom";
import userApi from "./UserApi";

export async function userGetHotels(){
    const res = await userApi.get("hotels");
    return res.data;
}
export async function bookingDetails() {
    const res = await userApi.get('booking');
    return res.data;
}
export async function bookHotel(data) {
    const res = await userApi.post('/book',data);
    return res.data;

}