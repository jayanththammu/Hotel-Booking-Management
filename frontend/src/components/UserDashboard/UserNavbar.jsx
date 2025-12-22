import React from "react";
import "../../styles/UserDashboard/UserNav.css";
import logo1 from "../../assets/User/logo.svg";
import userlogo from "../../assets/User/userlogo.jpg";

import { logoutUser } from "../../api/Auth";
import { Link, useNavigate } from "react-router-dom";
const UserNavBar = ({ userName, userId }) => {
  const navigate = useNavigate();
  const logout = async () => {
    try {
      const res = logoutUser();
      localStorage.clear();
      navigate("/");
    } catch (err) {}
  };

  return (
    <nav className="user_nav">
      <div className="left">
        <img src={logo1} alt="" />
        <Link to={`bookings/${userId}`}>Bookings</Link>
      </div>
      <div className="right">
        <div className="image">
          <img src={userlogo} alt="" />
        </div>
        <div className="nav_header">Welcome {userName}</div>
        <button id="logout" onClick={logout}>
          Log out
        </button>
      </div>
    </nav>
  );
};

export default UserNavBar;
