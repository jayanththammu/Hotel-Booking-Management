import React from 'react'
import '../styles/Login.css'
import { Link } from 'react-router-dom'
import LoginLogo from '../assets/Login Logo.svg'

const LoginComponent = ({formData,handleFormData,handleLogin,navigate }) => {
   const handleSubmit = (e)=>{
        e.preventDefault();
        handleLogin();
    }
  return (
    <div className='login_component'  onSubmit={handleSubmit} >
      <form action="">

     
        <img src={LoginLogo} alt=""  />
  
           
                <input type='text' id='username' placeholder='Username' required name='userName' value={formData.userName} onChange={(e)=>handleFormData(e)}/>
           
             
                <input type='password' id='password' placeholder='password' required name="password" value={formData.password} onChange={(e)=>handleFormData(e)}/>
       

  
   
            <button  >Sign In</button>
             </form>
            <div className="line"></div>

        <div className="login_footer">
            <Link to={navigate}>
               <span>sign Up</span>
            </Link>
        </div>
    </div>
  )
}

export default LoginComponent