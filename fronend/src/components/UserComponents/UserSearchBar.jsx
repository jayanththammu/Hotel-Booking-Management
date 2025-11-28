import React from 'react'
import search from './assests/search.svg'
 const UserSearchBar = () => {
  return (
    <div className='userSearch_Bar'>
        <img src={search} alt="not found" />
    <input type="text" placeholder='Search Hotels....' />
    </div>
  )
}

export default UserSearchBar