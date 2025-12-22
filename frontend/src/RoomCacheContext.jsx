import { createContext, useContext, useState } from "react";

const RoomCacheContext = createContext(null);
export const RoomCacheProvider = ({ children }) => {
  const [roomCache, setRoomCache] = useState({});

  

  const setRoomsForHotel = (hotelId, rooms) => {
    setRoomCache((prev) => ({
      ...prev,
      [hotelId]: rooms,
    }));
  };

  return (
    <RoomCacheContext.Provider value={{ roomCache, setRoomsForHotel }}>
      {children}
    </RoomCacheContext.Provider>
  );
};

export const useRoomCache = () => {
  const context = useContext(RoomCacheContext);
  if (!context) {
    throw new Error("useRoomCache must be used inside RoomCacheProvider");
  }

  return context;
};
