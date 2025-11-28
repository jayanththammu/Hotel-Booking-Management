import React, { useState } from 'react'
import Loading from '../components/Loading';
import { useSearchParams } from 'react-router-dom';
import { registerAdmin, RegisterUser } from '../api/Auth';

const SignUpPage = () => {
   const [searchParams] = useSearchParams();
    const role = searchParams.get('role') || 'user' ;
    const [showPass,setShowPass] = useState(false);
    const [password,setPassword] = useState('');
    const [cpassword,cSetPassword] = useState('');
    const [cshowPass,csetShowPass] = useState(false);
    const [msg,setMsg] = useState('');
    const [pr1,setPr1] = useState(false);
    const [pr2,setPr2] = useState(false);
    const [pr3,setPr3] = useState(false);
    const [pr4,setPr4] = useState(false);
    const [isLoading,setIsLoading] = useState(false);
    const [data,setData] = useState({  userName:'',
                                      name:'',
                                      email:'',
                                      phoneNumber:'',});

    const handleChange = (e)=>{
      const {name,value} = e.target;
      setData(prev => ({...prev,[name]:value}));
    }

    const handlePasswordChange = (e) => {
    const value = e.target.value;
    
    setPassword(value);

    setPr1(value.length >= 8);
    setPr2(/^[A-Z]/.test(value));
    setPr3(/[!@#$%^&*(),.?":{}|<>]/.test(value));
    setPr4(/[0-9]/.test(value));
  };

    const handleConfirmPasswordChange = (e) => {
  const value = e.target.value;
  cSetPassword(value);

  if (value === password && value !== '') {
    setMsg('✅ Passwords Match');
  } else if(value !== ''){
    setMsg('❌ Passwords Do Not Match');
  }
};

  // -----------------------------------SUBMIT LOGIC
  const handleSubmit = async ()=>{
    if(password !== cpassword){
          alert('password doesnt match');
          return;
        }
      try{

        setIsLoading(true);
        

        const payload = {...data,password};
        let response = '';
        if(role === 'user'){
          response = await RegisterUser(payload);
        }else{
          response = await registerAdmin(payload);
        }
        
        alert(`${role} ${response}`);

         setData({
      userName: '',
      name: '',
      email: '',
      phoneNumber: '',
    });
    setPassword('');
    cSetPassword('');
    setMsg('');
    setPr1(false);
    setPr2(false);
    setPr3(false);
    setPr4(false);


      }
      catch(err){

      }
      finally{
        setIsLoading(false);
      }
  };


  return (
    <div className='row '> 
       <div className="col ">
         { isLoading && <Loading/>}
         <h3 className='text-center f-30  t-sm l-sm'>Sign Up</h3>
        <label htmlFor="" className='label'>Name:</label>
        <input type="text" className='form-input r-sm i-focus' name='name' value={data.name} onChange={handleChange}/>

        <label htmlFor="" className='label'>Username:</label>
        <input type="text" className='form-input r-sm i-focus' name='userName' value={data.userName} onChange={handleChange}/>

        <label htmlFor="" className='label'>password:</label>
        <div className="input-group">
        <input type={ showPass ? "text":"password"} className='input r-sm i-focus' aria-describedby='p-r-1 p-r-2 p-r-3 p-r-4' value={password} onChange={handlePasswordChange}/>
        <button className='btn' onClick={()=>{setShowPass(!showPass)}}>{showPass ? "hide" : "show"} </button>
        </div>
        <div id="p-r-2" className={ pr2 ? 't-g' : 't-r'}>must Start with Capital letter.</div>
        <div id="p-r-1" className={ pr1 ? 't-g' : 't-r'}>must be at least 8 characters long.</div>
        <div id="p-r-3" className={ pr3 ? 't-g' : 't-r'}>must contain One Special Char.</div>
        <div id="p-r-4"className={ pr4 ? 't-g mb-3' : 't-r mb-3' }>must contain One Number.</div>



        <label htmlFor="" className='label'>Confirm password:</label>
        <div className="input-group">
          <input type={ cshowPass ? "text":"password"} className='form-input r-sm i-focus' aria-describedby='p-check' value={cpassword} onChange={handleConfirmPasswordChange}/>
         <button className='btn' onClick={()=>{csetShowPass(!cshowPass)}}>{cshowPass ? "hide" : "show"} </button>
        </div>
        <div id='p-check' className="mb-3">{msg}</div>
       

        <label htmlFor="" className='label' >phone number:</label>
        <input type="tel" className='form-input r-sm i-focus' name='phoneNumber' value={data.phoneNumber} onChange={handleChange}/>

        <label htmlFor="" className='label'>email:</label>
        <input type="text" className='form-input r-sm i-focus' name='email' value={data.email} onChange={handleChange}/>

        <button onClick={handleSubmit} className='btn btn-secondary r-sm'>Submit</button>

        
         
       </div>
    </div>
  )
}

export default SignUpPage