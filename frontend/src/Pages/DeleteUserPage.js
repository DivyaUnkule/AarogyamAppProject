import React, { useState } from 'react';
import axios from 'axios';

const DeleteUserPage = () => {
    const [userId, setUserId] = useState('');
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        setUserId(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            // Send the DELETE request with the user ID as a query parameter
            const response = await axios.delete('http://localhost:8080/users/admin/deleteUserById', {
                params: { id: userId }  // Sending ID as a URL parameter
            });
            setMessage('User deleted successfully');
            console.log(response.data);

            // Clear the form fields after successful deletion
            setUserId('');
        } catch (error) {
            console.error('Error deleting user:', error);
            setMessage('Error deleting user');
        }
    };

    return (
        <div className="container mt-4">
            <h1>Delete User</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="userId">User ID</label>
                    <input
                        type="text"
                        className="form-control"
                        id="userId"
                        value={userId}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-danger">Delete User</button>
            </form>
            {message && <p className="mt-3">{message}</p>}
        </div>
    );
};

export default DeleteUserPage;
