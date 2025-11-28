
export function FilteredHotels(hotels,search){
    return hotels.filter(hotel => hotel.hotelName.toLowerCase().includes(search.toLowerCase()));
}