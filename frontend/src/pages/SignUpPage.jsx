import React, { useState } from 'react'
import { registerAdmin, registerUser } from '../api/Auth';
import '../styles/SignUp.css'
import show from '../assets/show.png'

const SignUpPage = ({isUser}) => {

  const [emailError,setEmailError] = useState("");
  

  const [formData,setFormData] = useState({ userName :"",
                                                    firstName :'',
                                                    number:'',
                                                    email:'',
                                                    lastName:'',
                                                    password:''});
                                                                           
  const [showPass,setShowPass] = useState(false);
  const [showConfirmPass,setConfirmShowPass] = useState(false);

                                                    
  const handleChange = (e)=>{
    const {name,value} = e.target;
    setFormData({...formData,[name]:value});

    if(name == 'email')
      checkEmailError(value,setEmailError);
   
  }
 
 const submitUser = async()=>{
  console.log(formData);
    const res = await registerUser(formData);
    console.log(res);
 }

 const submitAdmin = async()=>{
  console.log(formData);
    const res = await registerAdmin(formData);
    console.log(res);
 }


  return (
    <div className='signup_page'>
      <div className="signup_header">SignUp</div>
      <div className="signup_form">

         <div className="signup_input_elements">
          <label htmlFor="">Email</label>
          <input type="email" 
          name="email"
          placeholder='Email'
          value={formData.email} 
          onChange={(e) => handleChange(e)}
          />
          { emailError && <span style={{color:'red'}}>{emailError}</span>}
 
        </div>

       { isUser && <div className="signup_input_elements">
          <label htmlFor="">First Name</label>
          <input type="text"
          name='firstName' 
          placeholder='First Name'
          value={formData.firstName} 
           onChange={(e) => handleChange(e)}
          />
        </div>}
      

       { isUser && <div className="signup_input_elements">
          <label htmlFor="">Last Name</label>
          <input type="text" 
          name='lastName'
          placeholder='Last Name'
          value={formData.lastName}
          onChange={(e) => handleChange(e)}
          />
        </div>
        }
        <div className="signup_input_elements">
          <label htmlFor="">userName</label>
          <input type="username" 
          name='userName'  
          placeholder='userName'
          value= {formData.userName}
          onChange={(e) => handleChange(e)}
          />
        </div>
        
        <div className="signup_input_elements">
          <label htmlFor="">Password</label>

         <div className="password_group">

           <input type={showPass ? "text" : "password"}
                  name= 'password'  
                  placeholder='password'
                  value= {formData.password} 
                  onChange={(e) => handleChange(e)}
           />
          <div className="show">
            <img onClick={()=>setShowPass(!showPass)} src={show} alt="" />
          </div>
         </div>
        
        </div>
        <div className="signup_input_elements">
          <label htmlFor="">Confirm Password</label>
           <div className="password_group">
               <input type={showConfirmPass ? 'text' : 'password'} name=' '  
          placeholder='confirm password'
          />
              <div className="show"><img onClick={()=>setConfirmShowPass(!showConfirmPass)} src={show} alt="" /></div>
           </div>
       
        </div>
        <div className="signup_input_elements">
          <label htmlFor="">Contact No</label>
          <input type="number" 
           name='number' 
           placeholder='number'
          value={formData.number }
          onChange={(e) => handleChange(e)}
           />
        </div>

         <div className="signup_btns">
        <button onClick={isUser ? submitUser : submitAdmin}>sign up</button>
      </div>
       

      </div>

     
    </div>
  )
}

export default SignUpPage

function checkEmailError(email,setEmailError){
   
  if(!/^\S+@\S+\.\S+$/.test(email)){
     setEmailError("InValid Email Format");
    
  }else
  setEmailError("");
}
function checkPasswordRules(password) {
  if (password.length < 6) return;


  // 1. First Character should not be a number
  if (/^[0-9]/.test(password)) {
    alert("First character should be a Letter");
    return false;
  }

  // 2. First character should be Capital
  if (/^[a-z]/.test(password)) {
    alert("First character should be a Capital Letter");
    return false;
  }

  // 3. At least one Special Character
  if (!/[~!@#$%^&*()_+\-={}[\]|:;"'<>,.?]/.test(password)) {
    alert("At least one Special Character is required");
    return false;
  }

  // 4. At least one Number ANYWHERE
  if (!/[0-9]/.test(password)) {
    alert("At least one Number is required");
    return false;
  }

  return true;
}
