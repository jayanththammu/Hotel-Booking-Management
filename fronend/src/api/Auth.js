import adminApi from "./AdminAPi";
import userApi from "./UserApi";


export async function loginUser(userName,password){
    
    // const response = await fetch(`${BASE_URL}/userserver/log`,{
    //   method:"POST",
    //   body: JSON.stringify({
    //     userName,
    //     password
    //   }),
    //   headers:{ "Content-Type" : "application/json"},
    //   credentials:"include"
    // });
    const response = await userApi.post('/log',{userName,password});
  return response.data;
}

export async function loginAdmin(userName,password){
    const response = await adminApi.post('/log',{userName,password});
    return response.data;

}
export async function  RegisterUser(data){

  try{
    const res = await userApi.post('/reg',data);
    return await res.data;
  }
  catch(err){
    console.log(err);
  }
}
export async function registerAdmin(data) {
  try {
    const res = await adminApi.post('reg',data);
    return await res.data;
  } catch (err) {
    console.log(err);
    
  }
}