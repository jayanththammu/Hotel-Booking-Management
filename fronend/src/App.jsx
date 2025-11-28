import React from "react";
 import { BrowserRouter, Routes, Route, Router } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import './styles/styles.css'
import './styles/AdminDashboard.css'
import './styles/AddHotelRoom.css'
import './styles/UserDashboard.css'

import SignUpPage from "./pages/SignUpPage";
import AdminDashbard from "./pages/AdminDashbard";
import { UserDashboard } from "./pages/UserDashboard";
function App() {
  return (
   <BrowserRouter>
    <Routes>
    <Route path="/" element={<LoginPage />} ></Route>
    <Route path="/signup" element={<SignUpPage />} ></Route>
    <Route path="/admin" element={<AdminDashbard />}></Route>
    <Route path="/user" element={<UserDashboard />}></Route>

    </Routes>
   </BrowserRouter>
  );
}

export default App;
