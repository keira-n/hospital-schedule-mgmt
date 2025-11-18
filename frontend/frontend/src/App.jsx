import {Routes, Route, Navigate, Outlet, useNavigate} from "react-router-dom";
import Dashboard from "./components/Dashboard.jsx";
import Employee from "./components/Employee.jsx";
import EmployeeForm from "./components/EmployeeForm.jsx";
import AllShifts from "./components/AllShifts.jsx"; 
import AddShiftForm from "./components/AddShiftForm.jsx";
import AllLeaveRequests from "./components/AllLeaveRequests.jsx";
import MainPage from "./components/MainPage.jsx"
import LogIn from "./components/LogIn.jsx"
import "./App.css";
import LeaveRequestForm from "./components/LeaveRequestForm.jsx";
import {useState} from "react";

// "Guard" for admin page
const ProtectedRoutes = ({ isLoggedIn }) => {
    if (!isLoggedIn) {
        // If not logged in, redirect to login
        return <Navigate to="/login" replace />;
    }
    // If logged in, show the child page (e.g., /admin)
    return <Outlet />;
};

function App() {
    // This is the "is logged in?" memory
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const navigate = useNavigate();

    // This function is passed to the Login page
    const handleLogin = () => {
        setIsLoggedIn(true);
    };

    // This function is for the "Log Out" button
    const handleLogout = () => {
        setIsLoggedIn(false);
        navigate("/"); // Go back to the public main view
    };
  return (
    <div className="App">
      <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/leaverequestform" element={<LeaveRequestForm />} />
          <Route path="/login" element={<LogIn onLogin={handleLogin}/>} />

          <Route element={<ProtectedRoutes isLoggedIn={isLoggedIn} />}>
            <Route path="/admin" element={<Dashboard />} />
            <Route path="/employees" element={<Employee />} />
            <Route path="/employees/new" element={<EmployeeForm />} />
            <Route path="/shifts" element={<AllShifts />} />
            <Route path="/shifts/new" element={<AddShiftForm />} />
            <Route path="/leaverequests" element={<AllLeaveRequests />} />
          </Route>

      </Routes>
    </div>
  );
}

export default App;

