import React, { useState, useEffect } from 'react';

// Helper function to format the date
const formatDate = (dateValue) => {
  if (!dateValue) return 'N/A';
  try {
    const date = new Date(dateValue);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Month is 0-indexed
    const year = date.getFullYear();
    return `${day}-${month}-${year}`;
  } catch (error) {
    return 'Invalid Date';
  }
};

function AllLeaveRequests() {
  const [requests, setRequests] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  // Function to fetch all requests
  const fetchRequests = async () => {
    // Don't set loading true here to avoid flickering on every update
    setError(null);
    try {
      const response = await fetch('http://localhost:8080/api/leaverequests');
      if (!response.ok) {
        throw new Error('Failed to fetch leave requests.');
      }
      const data = await response.json();
      setRequests(data);
    } catch (error) {
      console.error('Error fetching requests:', error);
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  // Fetch data when the component loads
  useEffect(() => {
    fetchRequests();
  }, []); 

  // --- 1. THIS IS THE FIXED UPDATE FUNCTION ---
  const handleStatusChange = async (requestId, newStatus) => {
    // newStatus comes directly from the <select> value: "Approved", "Rejected", or "Pending"
    setError(null);
    
    let endpointSuffix = "";
    
    // Map the dropdown value to the correct backend endpoint
    if (newStatus === "Approved") {
        endpointSuffix = "approve";
    } else if (newStatus === "Rejected") {
        endpointSuffix = "reject";
    } else if (newStatus === "Pending") {
        endpointSuffix = "pend";
    } else {
        console.error("Unknown status:", newStatus);
        return;
    }
    
    try {
      // Call the correct endpoint: /api/leaverequests/{id}/{action}
      const response = await fetch(`http://localhost:8080/api/leaverequests/${requestId}/${endpointSuffix}`, {
        method: 'PUT',
      });

      if (!response.ok) {
        throw new Error(`Failed to update status to ${newStatus}.`);
      }

      // Refresh the list to show the new status from the database
      fetchRequests();

    } catch (error) {
      console.error('Error updating status:', error);
      setError(error.message);
    }
  };
  // --- END OF FIX ---

  // Styling
  const containerStyle = { fontFamily: 'Arial, sans-serif', margin: '2rem auto', padding: '2rem', maxWidth: '1000px', backgroundColor: '#f9f9f9', borderRadius: '8px', boxShadow: '0 4px 12px rgba(0,0,0,0.05)' };
  const tableStyle = { width: '100%', borderCollapse: 'collapse', marginTop: '1.5rem' };
  const thStyle = { backgroundColor: '#007bff', color: 'white', padding: '0.75rem', border: '1px solid #ddd', textAlign: 'left' };
  const tdStyle = { padding: '0.75rem', border: '1px solid #ddd', backgroundColor: '#fff' };
  
  // Dynamic style for the select box based on status
  const getSelectStyle = (status) => ({
    padding: '0.5rem',
    border: '1px solid #ccc',
    borderRadius: '4px',
    fontSize: '0.9rem',
    fontWeight: 'bold',
    color: status === 'Approved' ? '#155724' : (status === 'Rejected' ? '#721c24' : '#856404'),
    backgroundColor: status === 'Approved' ? '#d4edda' : (status === 'Rejected' ? '#f8d7da' : '#fff3cd'),
    cursor: 'pointer'
  });

  if (loading) {
    return <div style={containerStyle}><h2>Loading Leave Requests...</h2></div>;
  }
  
  return (
    <div style={containerStyle}>
      <h2>All Leave Requests</h2>
      {error && <p style={{ color: 'red' }}>Error: {error}</p>}
      
      {requests.length === 0 ? (
        <p>No leave requests found.</p>
      ) : (
        <table style={tableStyle}>
          <thead>
            <tr>
              <th style={thStyle}>Employee ID</th>
              <th style={thStyle}>Start Date</th>
              <th style={thStyle}>End Date</th>
              <th style={thStyle}>Reason</th>
              <th style={thStyle}>Status</th>
            </tr>
          </thead>
          <tbody>
            {requests.map((request) => (
              // Use request.id (the String _id) as key
              <tr key={request.id}>
                <td style={tdStyle}>{request.employeeId}</td>
                <td style={tdStyle}>{formatDate(request.startDate)}</td>
                <td style={tdStyle}>{formatDate(request.endDate)}</td>
                <td style={tdStyle}>{request.reason}</td>
                
                {/* --- 2. THE DROPDOWN --- */}
                <td style={tdStyle}>
                  <select 
                    style={getSelectStyle(request.status)}
                    value={request.status || 'Pending'} // Default to Pending if null
                    onChange={(e) => handleStatusChange(request.id, e.target.value)}
                  >
                    <option value="Pending">Pending</option>
                    <option value="Approved">Approved</option>
                    <option value="Rejected">Rejected</option>
                  </select>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default AllLeaveRequests;