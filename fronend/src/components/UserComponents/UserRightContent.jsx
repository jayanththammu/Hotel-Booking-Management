import React from 'react'
import UserSearchBar from './UserSearchBar'
import UserHotelContent from './UserHotelContent'

export const UserRightContent = () => {
  return (
    <div className='user_right_content'>
        <UserSearchBar />
        <UserHotelContent />
    </div>
  )
}
