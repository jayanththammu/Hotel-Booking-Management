import React from 'react'

const SearchBar = ({setSearch,search}) => {
  return (
   <input type='text'placeholder='Search ...' className='form-input bg-img w-100' value={search}
    onChange={(e)=>{ setSearch(e.target.value)}}></input>
  )
}

export default SearchBar