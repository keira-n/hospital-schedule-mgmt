import { Routes, Route } from "react-router-dom"; 
import Dashboard from "./components/Dashboard.jsx";
import Employee from "./components/Employee.jsx";
import EmployeeForm from "./components/EmployeeForm.jsx";
import AllShifts from "./components/AllShifts.jsx"; 
import AddShiftForm from "./components/AddShiftForm.jsx";
import AllLeaveRequests from "./components/AllLeaveRequests.jsx";
import MainPage from "./components/MainPage.jsx"
import "./App.css";
import LeaveRequestForm from "./components/LeaveRequestForm.jsx";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<MainPage />} />
          <Route path="/admin" element={<Dashboard />} />
        <Route path="/employees" element={<Employee />} />
        <Route path="/employees/new" element={<EmployeeForm />} />
        <Route path="/shifts" element={<AllShifts />} /> 
        <Route path="/shifts/new" element={<AddShiftForm />} />
        <Route path="/leaverequests" element={<AllLeaveRequests />} />
        <Route path="/leaverequestform" element={<LeaveRequestForm />} />
        <Route path="/login" element={<LogIn/>} />
      </Routes>
    </div>
  );
}

export default App;

