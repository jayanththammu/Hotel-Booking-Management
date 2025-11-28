import React from 'react'
import { Link } from 'react-router-dom'
const LoginCard = (props) => {
  return (
    <div className='container bg-dg rounded shadow'>
        <div className="col">
                <h3 className='text-center fs-20 w-1000 t-w'>{props.role} Login</h3>
                <label htmlFor="username" className='label fs-20'>Username:</label>
                <input type="text" className='form-input p-1 r-sm ' id='username' placeholder='username'
                 value={props.username}
                 onChange={ e => props.setUserName(e.target.value)}/>
           
            
                 <label htmlFor="password" className='label fs-20'>password:</label>
                <input type="password" className='form-input p-1 r-sm ' id='password'placeholder='password'
                value={props.password}
                onChange={e => props.setPassword(e.target.value)}/>
            <div className="row">
                <button onClick={props.handle} className='btn btn-primary btn-outline-none mx r-sm '>Login</button>
                 <Link to={`/signup?role=${props.role.toLowerCase()}`} >
                <button  className='btn btn-secondary btn-outline-none mx r-sm'>Sign Up</button>
                 </Link>

            </div>
        </div>
                

    </div>
  )
}

export default LoginCard
 