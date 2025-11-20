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
    setError(null);
    try {
      // Note: Ensure your backend allows CORS if running on different ports
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

  const handleStatusChange = async (requestId, newStatus) => {
    setError(null);
    
    let endpointSuffix = "";
    
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
      const response = await fetch(`http://localhost:8080/api/leaverequests/${requestId}/${endpointSuffix}`, {
        method: 'PUT',
      });

      if (!response.ok) {
        throw new Error(`Failed to update status to ${newStatus}.`);
      }

      fetchRequests();

    } catch (error) {
      console.error('Error updating status:', error);
      setError(error.message);
    }
  };

  // --- STYLES ---

  // 1. New Page Wrapper: Holds the background image and centers the content
  const pageWrapperStyle = {
    minHeight: '100vh',
    width: '100%',
    // Assuming background-2.png is in your public folder
    backgroundImage: `url('/background2.jpg')`, 
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat',
    backgroundAttachment: 'fixed', // Keeps background still while scrolling
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'flex-start'
  };
  const containerStyle = { padding: '2rem', margin: '2rem', backgroundColor: 'white', borderRadius: '10px', width: '90%', maxWidth: '1000px'};
  const tableStyle = { width: '100%', borderCollapse: 'collapse', fontSize: '0.95rem' };
  const thStyle = { padding: '0.75rem', borderBottom: '1px solid #e5e7eb' };
  const tdStyle = { padding: '0.75rem', textAlign: 'center' };
  
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
    return (
      <div style={pageWrapperStyle}>
        <div style={containerStyle}>
           <h2 style={{textAlign: 'center', color: '#555'}}>Loading Leave Requests...</h2>
        </div>
      </div>
    );
  }
  
  return (
    <div style={pageWrapperStyle}>
      <div style={containerStyle}>
        <h2 style={{ borderBottom: '2px solid #eee', paddingBottom: '1rem', marginBottom: '1rem' }}>
          All Leave Requests
        </h2>
        
        {error && (
          <div style={{ padding: '10px', backgroundColor: '#ffebee', color: '#c62828', borderRadius: '4px', marginBottom: '1rem' }}>
            Error: {error}
          </div>
        )}
        
        {requests.length === 0 ? (
          <p>No leave requests found.</p>
        ) : (
          <div style={{ overflowX: 'auto' }}> {/* Handle horizontal scroll on small screens */}
            <table style={tableStyle}>
              <thead>
                <tr style={{ backgroundColor: '#e6e6e6ff', textAlign: 'center'}}>
                  <th style={{...thStyle, width: '20%'}}>EMPLOYEE ID</th>
                  <th style={{...thStyle, width: '20%'}}>START DATE</th>
                  <th style={{...thStyle, width: '20%'}}>END DATE</th>
                  <th style={{...thStyle, width: '30%'}}>REASON</th>
                  <th style={{...thStyle, width: '10%'}}>STATUS</th>
                </tr>
              </thead>
              <tbody>
                {requests.map((request) => (
                  <tr key={request.id} style={{ borderBottom: '1px solid #e6e6e6ff' }}>
                    <td style={tdStyle}>{request.employeeId}</td>
                    <td style={tdStyle}>{formatDate(request.startDate)}</td>
                    <td style={tdStyle}>{formatDate(request.endDate)}</td>
                    <td style={{...tdStyle, textAlign: 'left'}}>{request.reason}</td>
                    <td style={tdStyle}>
                      <select 
                        style={getSelectStyle(request.status)}
                        value={request.status || 'Pending'}
                        onChange={(e) => handleStatusChange(request.id, e.target.value)}
                      >
                        <option style={{backgroundColor: '#fff3cd', color: '#856404'}} value="Pending">Pending</option>
                        <option style={{backgroundColor: '#d4edda', color: '#155724'}} value="Approved">Approved</option>
                        <option style={{backgroundColor: '#f8d7da', color: '#721c24'}} value="Rejected">Rejected</option>
                      </select>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}

export default AllLeaveRequests;