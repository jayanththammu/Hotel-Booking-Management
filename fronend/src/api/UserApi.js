import axios from "axios";

const userApi=  axios.create(
    {
        baseURL:'http://localhost:8080/userserver',
        withCredentials:true
    }
)
export default userApi