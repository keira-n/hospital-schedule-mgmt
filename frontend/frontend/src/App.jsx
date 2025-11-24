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

const ProtectedRoutes = ({ isLoggedIn }) => {
    if (!isLoggedIn) {
        return <Navigate to="/login" replace />;
    }
    return <Outlet />;
};

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const navigate = useNavigate();

    const handleLogin = () => {
        setIsLoggedIn(true);
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

