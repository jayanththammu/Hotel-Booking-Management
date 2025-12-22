import adminApi from "./AdminAPi";
import userApi from "./UserApi";

export async function loginUser(data) {
  const response = await userApi.post("/login", data);
  return response.data;
}

export async function loginAdmin(data) {
  const response = await adminApi.post("/login", data);
  return response.data;
}
export async function registerUser(data) {
  try {
    const res = await userApi.post("/register", data);
    return await res.data;
  } catch (err) {
    console.log(err);
  }
}
export async function registerAdmin(data) {
  try {
    const res = await adminApi.post("register", data);
    return await res.data;
  } catch (err) {
    console.log(err);
  }
}
export async function logoutAdmin() {
  try {
    const res = await adminApi.get("/logout");
    return res.data;
  } catch (err) {
    console.log(err);
  }
}
export async function logoutUser() {
  try {
    const res = await userApi.get("/logout");
    return res.data;
  } catch (err) {
    console.log(err);
  }
}
