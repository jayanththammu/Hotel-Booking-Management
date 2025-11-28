import React from 'react'
import SearchBar from '../components/SearchBar'
import UserHotelCard from '../components/UserComponents/UserHotelCard'
import UserLeftBar from '../components/UserComponents/UserLeftBar'
import { UserRightContent } from '../components/UserComponents/UserRightContent'
 

export function UserDashboard() {

  return (
    <div className='user_dashboard'>
      <header>
        <h2 className='text-center user_header'>Hotel Hub</h2>
      </header>
     <div className="user_main">
      <UserLeftBar />
      <UserRightContent />
     </div>
    </div>
  )
}
