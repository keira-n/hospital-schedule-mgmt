import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

// --- 1. THIS FUNCTION IS NOW SMARTER ---
const formatDate = (dateValue) => {
  if (!dateValue) return 'N/A';

  try {
    // Create a new Date object from the database string
    // (e.g., "2025-12-21T00:00:00.000Z")
    const date = new Date(dateValue);
    
    // Get the parts
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Month is 0-indexed
    const year = date.getFullYear();
    
    // Return the new format
    return `${day}-${month}-${year}`;

  } catch (error) {
    console.error("Could not format date:", dateValue);
    return 'Invalid Date';
  }
};
// --- END OF HELPER FUNCTION ---


function AllShifts() {
  const [shifts, setShifts] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const fetchShifts = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch('http://localhost:8080/api/shifts');
      if (!response.ok) {
        throw new Error('Failed to fetch shifts.');
      }
      const data = await response.json();
      setShifts(data);
    } catch (error) {
      console.error('Error fetching shifts:', error);
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchShifts();
  }, []); 

  const handleDelete = async (shiftId) => {
    setError(null); 
    
    if (!shiftId) {
        setError("Cannot delete: Invalid shift ID.");
        return;
    }

    try {
      const response = await fetch(`http://localhost:8080/api/shifts/${shiftId}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error('Failed to delete shift.');
      }
      
      fetchShifts(); // Refresh the list

    } catch (error) {
      console.error('Error deleting shift:', error);
      setError(error.message);
    }
  };
  
  // Styling (no changes)
  const containerStyle = { fontFamily: 'Arial, sans-serif', margin: '2rem auto', padding: '2rem', maxWidth: '900px', backgroundColor: '#f9f9f9', borderRadius: '8px', boxShadow: '0 4px 12px rgba(0,0,0,0.05)' };
  const tableStyle = { width: '100%', borderCollapse: 'collapse', marginTop: '1.5rem' };
  const thStyle = { backgroundColor: '#007bff', color: 'white', padding: '0.75rem', border: '1px solid #ddd', textAlign: 'center' };
  const tdStyle = { padding: '0.75rem', border: '1px solid #ddd', backgroundColor: '#fff' };
  const buttonStyle = { padding: '0.5rem 1rem', border: 'none', borderRadius: '4px', backgroundColor: '#28a745', color: 'white', fontSize: '1rem', cursor: 'pointer', textDecoration: 'none' };
  const headerStyle = { display: 'flex', justifyContent: 'space-between', alignItems: 'center' };
  const deleteButtonStyle = { padding: '0.3rem 0.6rem', border: 'none', borderRadius: '4px', backgroundColor: '#dc3545', color: 'white', fontSize: '0.9rem', cursor: 'pointer' };
  
  if (loading) {
    return <div style={containerStyle}><h2>Loading All Shifts...</h2></div>;
  }
  
  return (
    <div style={containerStyle}>
      <div style={headerStyle}>
        <h2>SHIFTS</h2>
        <Link to="/shifts/new" style={buttonStyle}>
          Add New Shift
        </Link>
      </div>
      
      {error && <p style={{ color: 'red' }}>Error: {error}</p>}

      {shifts.length === 0 ? (
        <p>No shifts found in the database.</p>
      ) : (
        <table style={tableStyle}>
          <thead>
            <tr>
              <th style={thStyle}>Employee ID</th>
              <th style={thStyle}>Role</th>
              <th style={thStyle}>Date (DD-MM-YYYY)</th>
              <th style={thStyle}>Start Time</th>
              <th style={thStyle}>End Time</th>
              <th style={thStyle}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {shifts.map((shift) => (
              // --- 1. THE FIX ---
              // The key is now just shift.id
              <tr key={shift.id}>
                <td style={tdStyle}>{shift.employeeId}</td>
                <td style={tdStyle}>{shift.role}</td>
                <td style={tdStyle}>{formatDate(shift.date)}</td>
                <td style={tdStyle}>{shift.startTime}</td>
                <td style={tdStyle}>{shift.endTime}</td>
                
                <td style={tdStyle}>
                  <button 
                    style={deleteButtonStyle} 
                    // --- 2. THE FIX ---
                    // We just pass shift.id directly
                    onClick={() => handleDelete(shift.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default AllShifts;