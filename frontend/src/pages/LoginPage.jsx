import React, { useState } from "react";
import LoginComponent from "../components/LoginComponent";
import { loginAdmin, loginUser } from "../api/Auth";
import { useNavigate } from "react-router-dom";
import Loading from "../components/Loading";

const LoginPage = () => {
  const navigate = useNavigate();
  const [userFormData, setUserFormData] = useState({
    userName: "",
    password: "",
  });
  const [adminFormData, setAdminFormData] = useState({
    userName: "",
    password: "",
  });
  const [isLoading, setIsLoading] = useState(false);
  const handleUserFormData = (e) => {
    const { name, value } = e.target;
    setUserFormData({ ...userFormData, [name]: value });
  };
  const handleAminFormData = (e) => {
    const { name, value } = e.target;
    setAdminFormData({ ...adminFormData, [name]: value });
  };
  const handleAdminLogin = async () => {
    try {
      setIsLoading(true);
      const res = await loginAdmin(adminFormData);
      localStorage.setItem("isloggedIn", "true");

      navigate("/admin");
    } catch (error) {
    } finally {
      setAdminFormData({ userName: "", password: "" });
      setIsLoading(false);
    }
  };
  const handleUserLogin = async () => {
    try {
      setIsLoading(true);
      const res = await loginUser(userFormData);

      localStorage.setItem("userLoggedIn", "true");
      localStorage.setItem("userCredentials", JSON.stringify(res));
      navigate("/user");
    } catch (error) {
    } finally {
      setUserFormData({ userName: "", password: "" });
      setIsLoading(false);
    }
  };

  //  admin logic
  const [isAdmin, setIsAdmin] = useState(false);

  return (
    <div className="login_page">
      {isLoading && <Loading />}
      <button
        className="admin_button"
        onClick={() => (isAdmin ? setIsAdmin(false) : setIsAdmin(true))}
      >
        {isAdmin ? "user" : "Admin"}
      </button>
      <div className="login_left">
        <div className="login_left_content">
          <div className="login_left_content_header">
            Your Perfect Stay Awaits
          </div>
          <p className="login_left_content_para">
            Book premium rooms, enjoy exceptional service, and relax in a
            peaceful atmosphere.
          </p>
        </div>
      </div>
      <div
        className="login_right"
        style={isAdmin ? { backgroundColor: "#005461" } : {}}
      >
        {!isAdmin && (
          <LoginComponent
            formData={userFormData}
            handleFormData={handleUserFormData}
            handleLogin={handleUserLogin}
            navigate="/signup/user"
          />
        )}
        {isAdmin && (
          <LoginComponent
            formData={adminFormData}
            handleFormData={handleAminFormData}
            handleLogin={handleAdminLogin}
            navigate="/signup/admin"
          />
        )}
      </div>
    </div>
  );
};

export default LoginPage;
