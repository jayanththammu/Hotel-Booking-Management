import userApi from "./UserApi";

export async function getHotelsUsers() {
  try {
    const res = await userApi.get("/hotels");
    return res.data;
  } catch (error) {
    return error;
  }
}

export async function getHotelDetails(hotelId) {
  try {
    const res = await userApi.get(`rooms/${hotelId}`);

    return res.data;
  } catch (error) {
    return error;
  }
}
export async function bookHotel(data) {
  try {
    const res = await userApi.post("book", data);
    return res.data;
  } catch (err) {
    return err;
  }
}
export async function getBookings(userId) {
  try {
    const res = await userApi.get(`bookings/${userId}`);
    return res.data;
  } catch (err) {
    return err;
  }
}
