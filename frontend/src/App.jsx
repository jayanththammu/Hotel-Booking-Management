import React from "react";
import { BrowserRouter, Routes, Route, Router } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import SignUpPage from "./pages/SignUpPage";
import AdminDashboard from "./pages/AdminDashboard";
import { ProtectedRoute, UserProctedRoute } from "./router/ProtectedRoute";
import UserDashboard from "./pages/UserDashboard";
import HotelContainer from "./components/UserDashboard/HotelContainer";
import HotelDetails from "./components/UserDashboard/HotelDetails";
import BookingComponent from "./components/UserDashboard/BookingComponent";
import Bookings from "./components/UserDashboard/Bookings";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />}></Route>
        <Route
          path="/signup/admin"
          element={<SignUpPage isUser={false} />}
        ></Route>
        <Route
          path="/signup/user"
          element={<SignUpPage isUser={true} />}
        ></Route>
        <Route
          path="/admin"
          element={
            <ProtectedRoute>
              <AdminDashboard />
            </ProtectedRoute>
          }
        ></Route>
        <Route
          path="/user"
          element={
            <UserProctedRoute>
              <UserDashboard />
            </UserProctedRoute>
          }
        >
          <Route index element={<HotelContainer />} />
          <Route path="hotel/:id" element={<HotelDetails />}></Route>
          <Route
            path="book/:hotelId/:roomId/:roomPrice"
            element={<BookingComponent />}
          ></Route>
          <Route path="bookings/:userId" element={<Bookings />}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
