import React, { useState } from 'react';
import axios from 'axios';

const UserDetailsPage = () => {
    const [userId, setUserId] = useState('');
    const [user, setUser] = useState(null);
    const [error, setError] = useState(null);

    const fetchUserDetails = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/users/admin/getUserById/${userId}`);
            setUser(response.data);
            setError(null);
        } catch (error) {
            console.error('Error fetching user details:', error);
            setError('User not found or an error occurred.');
            setUser(null);
        }
    };

    const handleInputChange = (event) => {
        setUserId(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if (userId) {
            fetchUserDetails();
        }
    };

    return (
        <div className="container mt-4">
            <h1>User Details</h1>
            <form onSubmit={handleSubmit} className="mb-4">
                <div className="form-group">
                    <label htmlFor="userIdInput">Enter User ID:</label>
                    <input
                        type="text"
                        className="form-control"
                        id="userIdInput"
                        value={userId}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary">
                    Get User Details
                </button>
            </form>
            {error && <p className="text-danger">{error}</p>}
            {user ? (
                <table className="table">
                    <tbody>
                        <tr>
                            <th>User ID</th>
                            <td>{user.userId}</td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>{user.email}</td>
                        </tr>
                        <tr>
                            <th>First Name</th>
                            <td>{user.firstName}</td>
                        </tr>
                        <tr>
                            <th>Last Name</th>
                            <td>{user.lastName}</td>
                        </tr>
                        <tr>
                            <th>Phone No</th>
                            <td>{user.phoneNo}</td>
                        </tr>
                        <tr>
                            <th>Status</th>
                            <td>{user.status}</td>
                        </tr>
                        <tr>
                            <th>Address</th>
                            <td>{user.address}</td>
                        </tr>
                        <tr>
                            <th>Gender</th>
                            <td>{user.gender}</td>
                        </tr>
                        <tr>
                            <th>Roles</th>
                            <td>
                                {user.roles ? user.roles.map(role => role.roleName).join(', ') : 'N/A'}
                            </td>
                        </tr>
                    </tbody>
                </table>
            ) : (
                !error && <p>Loading user details...</p>
            )}
        </div>
    );
};

export default UserDetailsPage;
