import React, { use, useEffect, useRef, useState } from "react";
import UserNavBar from "../components/UserDashboard/UserNavBar";
import "../styles/UserDashboard/UserDashboard.css";
import { getHotelsUsers } from "../api/UserServices";
import { Outlet } from "react-router-dom";
const UserDashboard = () => {
  const userName = JSON.parse(localStorage.getItem("userCredentials")).lastName;
  const userId = JSON.parse(localStorage.getItem("userCredentials")).id;

  const apiCalled = useRef(false);

  const [hotels, setHotels] = useState([]);
  useEffect(() => {
    if (apiCalled.current) return;
    apiCalled.current = true;
    getHotels();
  }, []);
  const getHotels = async () => {
    try {
      const res = await getHotelsUsers();
      setHotels(res);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="user_dashboard">
      <UserNavBar userName={userName} userId={userId} />
      <main>
        <Outlet context={{ hotels }} />
      </main>
    </div>
  );
};

export default UserDashboard;
