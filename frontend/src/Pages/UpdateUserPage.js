import React, { useState } from 'react';
import axios from 'axios';

const UpdateUserPage = () => {
    const [userDetails, setUserDetails] = useState({
        userId: '',
        email: '',
        firstName: '',
        lastName: '',
        phoneNo: '',
        status: '',
        address: '',
        gender: '',
        role: ''  // Role is now a single selection
    });
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUserDetails({ ...userDetails, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Prepare the role as an object
        const roleEntity = {
            id: getRoleIdByName(userDetails.role), // Method to get role ID by name
            roleName: userDetails.role
        };

        const updatedUserDetails = {
            ...userDetails,
            roles: [roleEntity]  // Sending roles as an array of role objects
        };

        try {
            const response = await axios.put('http://localhost:8080/users/admin/updateUserDetails', updatedUserDetails);
            setMessage('User details updated successfully');
            console.log(response.data);
        } catch (error) {
            console.error('Error updating user details:', error);
            setMessage('Error updating user details');
        }
    };

    // Example of a function to get role ID by name
    const getRoleIdByName = (roleName) => {
        switch (roleName) {
            case 'ROLE_ADMIN':
                return 1; // Assuming 1 is the ID of ROLE_ADMIN in your database
            case 'ROLE_REGULARUSER':
                return 2; // Assuming 2 is the ID of ROLE_REGULARUSER in your database
            case 'ROLE_WEIGHTLOSSUSER':
                return 4; // Assuming 3 is the ID of ROLE_WEIGHTLOSSUSER in your database
            case 'ROLE_WEIGHTGAINUSER':
                return 3; // Assuming 4 is the ID of ROLE_WEIGHTGAINUSER in your database
            default:
                return null;
        }
    };

    return (
        <div className="container mt-4">
            <h1>Update User Details</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="userId">User ID</label>
                    <input
                        type="text"
                        className="form-control"
                        id="userId"
                        name="userId"
                        value={userDetails.userId}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input
                        type="email"
                        className="form-control"
                        id="email"
                        name="email"
                        value={userDetails.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="firstName">First Name</label>
                    <input
                        type="text"
                        className="form-control"
                        id="firstName"
                        name="firstName"
                        value={userDetails.firstName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="lastName">Last Name</label>
                    <input
                        type="text"
                        className="form-control"
                        id="lastName"
                        name="lastName"
                        value={userDetails.lastName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="phoneNo">Phone No</label>
                    <input
                        type="text"
                        className="form-control"
                        id="phoneNo"
                        name="phoneNo"
                        value={userDetails.phoneNo}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="status">Status</label>
                    <input
                        type="text"
                        className="form-control"
                        id="status"
                        name="status"
                        value={userDetails.status}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="address">Address</label>
                    <input
                        type="text"
                        className="form-control"
                        id="address"
                        name="address"
                        value={userDetails.address}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="gender">Gender</label>
                    <select
                        className="form-control"
                        id="gender"
                        name="gender"
                        value={userDetails.gender}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Select Gender</option>
                        <option value="MALE">Male</option>
                        <option value="FEMALE">Female</option>
                        <option value="OTHER">Other</option>
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="role">Role</label>
                    <select
                        className="form-control"
                        id="role"
                        name="role"
                        value={userDetails.role}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Select Role</option>
                        <option value="ROLE_ADMIN">Admin</option>
                        <option value="ROLE_REGULARUSER">Regular User</option>
                        <option value="ROLE_WEIGHTLOSSUSER">Weight Loss User</option>
                        <option value="ROLE_WEIGHTGAINUSER">Weight Gain User</option>
                    </select>
                </div>
                <button type="submit" className="btn btn-primary">Update User</button>
            </form>
            {message && <p className="mt-3">{message}</p>}
        </div>
    );
};

export default UpdateUserPage;
