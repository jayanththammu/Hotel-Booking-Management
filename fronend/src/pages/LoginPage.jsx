import React, { useState } from 'react'
import LoginCard from '../components/LoginCard'
import Loading from '../components/Loading'
import { loginAdmin, loginUser } from '../api/Auth';
import { Link } from 'react-router-dom';

function LoginPage(){
  const [username,setUserName] = useState('');
  const [password,setPassword] = useState('');
  const [adminUsername,setAdminUserName] = useState('');
  const [adminPassword,setAdminPassword] = useState('');
  const [isLoading,setIsLoading] = useState(false);

  const handleUser = async ()=>{
   try{
      setIsLoading(true);
      const data =await loginUser(username,password);
      alert(data);
    }
    catch(err){
      alert(err);
    }
    finally{
    setIsLoading(false); 
    setUserName('');
    setPassword('');
    }
  }
  const handleAdmin =async ()=>{
    
    try{
      setIsLoading(true);
      const data =await loginAdmin(adminUsername,adminPassword);
      alert(data);
    }
    catch(err){
      alert(err);
    }
    finally{
    setIsLoading(false); 
    setAdminPassword('');
    setAdminUserName('');
    }
    
  }

  return (
    <div className='col bg-gr '>
      <h3 className='text-center f-30 t-s l-s'>Login </h3>

      {isLoading && <Loading />}
       
       <div className="row">
        <LoginCard role="User" username = {username} setUserName={setUserName} password ={password} setPassword={setPassword} handle={handleUser}/>
        <LoginCard role="Admin" username = {adminUsername} setUserName={setAdminUserName} password ={adminPassword} setPassword={setAdminPassword} handle={handleAdmin}/>
       </div>

        <Link to='admin'>
        <button>admin</button>
        </Link>

        <Link to='user'>
        <button>user</button>
        </Link>
    </div>
  )
}

export default LoginPage