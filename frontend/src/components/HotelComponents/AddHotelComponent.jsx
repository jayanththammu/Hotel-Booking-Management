import React, { useState } from 'react'
import { checkHotelData } from '../../pages/AdminDashboard';
import { toast, ToastContainer } from 'react-toastify';
import { addHotel } from '../../api/AdminServices';
const AddHotelComponent = ({fetchHotels}) => {

    const [hotelData,setHotelData] = useState({
      hotelName:'',
      hotelDescription:'',
      hotelRating:'',
    });


    const handleHotelData = (e) => {
        const { name, value } = e.target;
        setHotelData({ ...hotelData, [name]: value });
    }
    const saveHotel = async () => {
        if (!checkHotelData(hotelData.hotelName, hotelData.hotelDescription, hotelData.hotelRating)) {
            alert("Enter all fields");
            return;
        }
        try {
            const res = await addHotel(hotelData);
            if (res === 'Hotel Saved Successfully')
                toast.success("Hotel added successfully!")
            fetchHotels();

        }
        catch (err) {
            toast.error("Failed to Add Hotel")
        }
        finally {
            setHotelData({ hotelName: '', hotelDescription: '', hotelRating: '' });
        }

    }
    return (
        <div className="container">
            <ToastContainer />
            <div className="card_title">Add</div>

            <div className="input_group">
                <label htmlFor="hotelName">Hotel Name </label>
                <input type="text" id='hotelName'
                    name='hotelName'
                    value={hotelData.hotelName}
                    onChange={(e) => handleHotelData(e)} />
            </div>
            <div className="input_group">
                <label htmlFor="description">Description</label>
                <input type="text"
                    id='description'
                    name='hotelDescription'
                    value={hotelData.hotelDescription}
                    onChange={e => handleHotelData(e)} />
            </div>
            <div className="input_group">
                <label htmlFor="rating">Rating</label>
                <input type="text"
                    id='rating'
                    name='hotelRating'
                    value={hotelData.hotelRating}
                    onChange={e => handleHotelData(e)}
                />
            </div>

            <div className="card_footer">
                <button onClick={saveHotel}>Save</button>
            </div>
        </div>
    )
}

export default AddHotelComponent