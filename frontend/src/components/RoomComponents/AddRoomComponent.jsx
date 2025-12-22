import React, { useEffect, useState } from 'react'
import { getFilteredHotels } from '../../pages/AdminDashboard';
import Search from '../../assets/subtract.svg';
import backarrow from '../../assets/backarrow.svg';
import { toast } from 'react-toastify';
import { addRoom } from '../../api/AdminServices';
const AddRoomComponent = ({ hotels, hotelsMap }) => {

    const [showRoomDetails, setShowRoomDetails] = useState(false);
    const [search, setSearch] = useState("");
    const [selectedRoom, setSelectedRoom] = useState({});
    const [selectedHotel, setSelectedHotel] = useState('');
    const [filteredHotels, setFilteredHotels] = useState(hotels);


    const [roomData, setRoomData] = useState({
        'roomNumber': '',
        'noOfShares': '',
        'roomPrice': '',
        'roomType': '',
    });

    const handleSaveRoomDetails = async (e) => {
        e.preventDefault();

        try {
            console.log(selectedHotel);
            const res = await addRoom(roomData, selectedHotel);

            toast.success(res);

        } catch (error) {
            toast.error("failed To Save Room");
        }
        finally {
            setRoomData({
                'roomNumber': '',
                'noOfShares': '',
                'roomPrice': '',
                'roomType': '',
            });

        }

    }

    const closeSelectedRoom = () => {
        setSelectedHotel('');
        setShowRoomDetails(false);
    }

    const handleSelectedRoom = (id) => {
        setShowRoomDetails(true);
        setSelectedHotel(id);

        setSelectedRoom(hotelsMap[id]);
        setRoomData({
            roomNumber: "",
            noOfShares: "",
            roomPrice: "",
            roomType: ""
        });
    }
    const handleFilteredHotels = () => {
        const res = getFilteredHotels(hotels, search);
        setFilteredHotels(res);
    }


    useEffect(() => {
        handleFilteredHotels();
    }, [hotels, search]);
    const handleRoomData = (e) => {
        const { name, value } = e.target;

        setRoomData({ ...roomData, [name]: value });
    }

    return (
        <div className={!showRoomDetails ? "container room_container" : "container room_container"}>
            {showRoomDetails && <img className='backarrow' src={backarrow} alt="" onClick={closeSelectedRoom} />}
            {!showRoomDetails && <div className="room_header">
                <div className="room_header_text">Add Room</div>
                <div className="search_bar">
                    <input type="text" placeholder='Search.....' onChange={(e) => setSearch(e.target.value)} />
                    <img src={Search} alt="" width='15' />
                </div>
            </div>}
            {!showRoomDetails && <div className="grid_items">
                {filteredHotels.map(h => (
                    <div className='update_item' key={h.hotelId} onClick={() => handleSelectedRoom(h.hotelId)}>
                        {h.hotelName}
                    </div>))}
            </div>}

            {showRoomDetails && <div className="room_details">
                <div className='room_details_header'>{selectedRoom.hotelName}</div>
                <form onSubmit={handleSaveRoomDetails}>
                    <input type="number" placeholder='Room Number' name='roomNumber' value={roomData.roomNumber} onChange={(e) => handleRoomData(e)} required />

                    <select name="noOfShares" value={roomData.noOfShares} onChange={(e) => handleRoomData(e)} required>
                        <option value="">Shares</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>

                    <select name="roomType" value={roomData.roomType} onChange={(e) => handleRoomData(e)} required>
                        <option value="">Type</option>
                        <option value="Ac">Ac</option>
                        <option value="NonAc">Non-Ac</option>
                        <option value="Delux">Delux</option>
                        <option value="Suite">Suite</option>
                    </select>
                    <input type="number" placeholder='price' name="roomPrice" value={roomData.roomPrice} onChange={(e) => handleRoomData(e)} required />
                    <input type="submit" />
                </form>
            </div>}
        </div>
    )
}

export default AddRoomComponent