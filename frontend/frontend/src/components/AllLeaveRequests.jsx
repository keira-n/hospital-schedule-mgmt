import React, { useState, useEffect } from 'react';

// Helper function to format the date
const formatDate = (dateString) => {
  if (!dateString) return 'N/A';
  const parts = dateString.split('-');
  if (parts.length === 3) {
    const [year, month, day] = parts;
    return `${day}-${month}-${year}`;
  }
  return dateString;
};

function AllLeaveRequests() {
  const [requests, setRequests] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  // Function to fetch all requests
  const fetchRequests = async () => {
    try {
      // This URL is correct
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
  }, []); // Run only once on page load

  // The handleUpdateStatus function
  const handleUpdateStatus = async (requestId, newStatus) => {
    console.log(`Updating request ${requestId} to status: ${newStatus}`);
  };

  // Styling
  const containerStyle = { fontFamily: 'Arial, sans-serif', margin: '2rem auto', padding: '2rem', maxWidth: '1000px', backgroundColor: '#f9f9f9', borderRadius: '8px', boxShadow: '0 4px 12px rgba(0,0,0,0.05)' };
  const tableStyle = { width: '100%', borderCollapse: 'collapse', marginTop: '1.5rem' };
  const thStyle = { backgroundColor: '#007bff', color: 'white', padding: '0.75rem', border: '1px solid #ddd', textAlign: 'center' };
  const tdStyle = { padding: '0.75rem', border: '1px solid #ddd', backgroundColor: '#fff' };

  if (loading) {
    return <div style={containerStyle}><h2>Loading Leave Requests...</h2></div>;
  }
  if (error) {
    return <div style={containerStyle}><h2>Error: {error}</h2></div>;
  }

  return (
    <div style={containerStyle}>
      <h2>LEAVE REQUESTS</h2>
      
      {requests.length === 0 ? (
        <p>No leave requests found.</p>
      ) : (
        <table style={tableStyle}>
          <thead>
            <tr>
              <th style={{...thStyle, width: '30%'}}>Employee ID</th>
              <th style={{...thStyle, width: '20%'}}>Start Date</th>
              <th style={{...thStyle, width: '20%'}}>End Date</th>
              <th style={{...thStyle, width: '25%'}}>Reason</th>
              <th style={{...thStyle, width: '5%'}}>Status</th>
            </tr>
          </thead>

          <tbody>
            {requests.map((request, index) => (
              // The key is now _id (from MongoTemplate)
              <tr key={request._id || index}> 
                <td style={tdStyle}>{request.employeeId}</td>
                <td style={tdStyle}>{formatDate(request.startDate)}</td>
                <td style={tdStyle}>{formatDate(request.endDate)}</td>
                <td style={tdStyle}>{request.reason}</td>
                <td style={{ ...tdStyle, textAlign: 'center' }}>
                  <select 
                    value={request.status || 'Pending'}
                    onChange={(e) => handleUpdateStatus(request._id, e.target.value)}
                    style={{
                      fontWeight: 'bold',
                      padding: '0.4rem',
                      outline: 'none',
                      cursor: 'pointer',
                      borderRadius: '4px',
                      color: request.status == 'Approved' ? 'green' : (request.status == 'Rejected' ? 'red' : '#FFC246'),
                      backgroundColor: request.status == 'Approved' ? '#d4edda' : (request.status == 'Rejected' ? '#f8d7da' : '#fff3cd')
                    }}
                  >
                    <option value="Pending" style={{ fontWeight: 'bold', color: '#FFC246', backgroundColor: '#fff3cd' }}>Pending</option>
                    <option value="Approved" style={{ fontWeight: 'bold', color: 'green', backgroundColor: '#d4edda' }}>Approved</option>
                    <option value="Rejected" style={{ fontWeight: 'bold', color: 'red', backgroundColor: '#f8d7da' }}>Rejected</option>
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