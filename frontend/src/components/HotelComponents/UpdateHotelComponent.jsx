import React, { useState } from 'react'
import { checkHotelData } from '../../pages/AdminDashboard';
import { updateHotel } from '../../api/AdminServices';
import { toast, ToastContainer } from 'react-toastify';
import backarrow from '../../assets/backarrow.svg';


const UpdateHotelComponent = ({ hotels, hotelsMap, fetchHotels }) => {

  const [showDefault, setShowDefault] = useState(true);
  const [hotelDetails, setHotelDetails] = useState({});
  const [updateData, setUpdateData] = useState({
    hotelDescription: '',
    hotelRating: '',
  });
  const [selectedHotel, setSelectedHotel] = useState('');

  const closeUpdate = () => {

    setShowDefault(true);
    setSelectedHotel('');
  }

  const handleUpdateHotel = (id) => {

    setSelectedHotel(id);
    setShowDefault(false);

    const selected = hotelsMap[id];
    setHotelDetails(selected);
    setUpdateData(selected);

  }

  const handleUpdateHotelData = (e) => {
    const { name, value } = e.target;

    setUpdateData({ ...updateData, [name]: value });
  }

  const handleUpdateHotelSubmit = async () => {
    if (!checkHotelData(updateData.hotelName, updateData.hotelDescription, updateData.hotelRating)) {
      alert('Fill All fields');
      return;
    }

    try {
      const res = await updateHotel(updateData, selectedHotel);
      fetchHotels();
      toast.success(res);


    }
    catch (err) {
      toast.error('Failed To Update Hotel Data');
    }
    finally {
      setUpdateData({
        hotelDescription: '',
        hotelRating: '',
      });
    }


  }
  return (
    <div className={showDefault ? 'container updatecard' : "container updatecard slide-fade-in"}>
      {!showDefault && <img className='backarrow' src={backarrow} alt="" onClick={closeUpdate} />}

      <div className="card_title ">Update</div>
      <ToastContainer />
      {showDefault && <div className="grid_items">
        {hotels.map(h => (
          <div className='update_item' key={h.hotelId} onClick={() => handleUpdateHotel(h.hotelId)}>
            {h.hotelName}
          </div>))}
      </div>}
      {
        selectedHotel && <div className="input_group">
          <label htmlFor="hotelName">Hotel Name </label>
          <input type="text" id='hotelName'
            name='hotelName'
            value={hotelDetails.hotelName}
            style={{ color: 'white', backgroundColor: 'black' }}
            disabled
          />
        </div>
      }
      {selectedHotel && <div className="input_group">
        <label htmlFor="description">Description</label>
        <input type="text"
          id='description'
          name='hotelDescription'
          value={updateData.hotelDescription}
          onChange={e => handleUpdateHotelData(e)}
        />
      </div>}
      {selectedHotel && <div className="input_group">
        <label htmlFor="rating">Rating</label>
        <input type="text"
          id='rating'
          name='hotelRating'
          value={updateData.hotelRating}
          onChange={e => handleUpdateHotelData(e)}

        />
      </div>}
      {selectedHotel && <div className="card_footer">
        <button onClick={handleUpdateHotelSubmit}>update</button>
      </div>}

    </div>
  )
}

export default UpdateHotelComponent