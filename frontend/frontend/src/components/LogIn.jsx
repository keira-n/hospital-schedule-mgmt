import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function LogIn({ onLogin }) {
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);

    // This is the hook that lets us change pages
    const navigate = useNavigate();

    // This is your password
    const ADMIN_PASSWORD = '12345678';

    // This function runs when you click the "Log In" button
    const handleSubmit = (event) => {
        event.preventDefault();
        setError(null);

        // Check if the password is correct
        if (password === ADMIN_PASSWORD) {

            // 1. This tells App.jsx that you are logged in
            onLogin();

            // 2. This sends you to the admin dashboard page
            navigate("/admin");

        } else {
            // 3. This shows an error if the password is wrong
            setError('Invalid password. Please try again.');
            setPassword('');
        }
    };

    // Styling
    const formStyle = { margin: '5rem auto', padding: '2rem', maxWidth: '400px', boxShadow: '0 4px 12px rgba(0,0,0,0.05)', borderRadius: '8px', backgroundColor: '#fff' };
    const divStyle = { marginBottom: '1rem', display: 'flex', flexDirection: 'column' };
    const labelStyle = { fontWeight: '600', marginBottom: '0.5rem' };
    const inputStyle = { padding: '0.75rem', border: '1px solid #ccc', borderRadius: '4px', fontSize: '1rem' };
    const buttonStyle = { padding: '0.75rem 1.5rem', border: 'none', borderRadius: '4px', backgroundColor: '#007bff', color: 'white', fontSize: '1rem', cursor: 'pointer' };

    return (
        <form onSubmit={handleSubmit} style={formStyle}>
            <h2>Admin Login</h2>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <div style={divStyle}>
                <label style={labelStyle}>Password:</label>
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    style={inputStyle}
                />

            </div>

            <button type="submit" style={buttonStyle}>Log In</button>
        </form>
    );
}

export default LogIn;