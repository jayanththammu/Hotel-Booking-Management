import { Navigate } from "react-router-dom";

export function ProtectedRoute({ children }) {
  const loggedIn = localStorage.getItem("isloggedIn") === "true";

  return loggedIn ? children : <Navigate to="/" replace />;
}
export function UserProctedRoute({ children }) {
  const loggedIn = localStorage.getItem("userLoggedIn") === "true";

  return loggedIn ? children : <Navigate to="/" replace />;
}
