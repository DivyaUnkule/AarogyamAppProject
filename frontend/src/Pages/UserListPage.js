import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UserListPage = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/users/admin/getAllUsers');
                setUsers(response.data);
            } catch (error) {
                console.error('Error fetching users:', error);
            }
        };

        fetchUsers();
    }, []);

    return (
        <div className="container mt-4">
            <h1>User List</h1>
            <table className="table">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Phone No</th>
                        <th>Status</th>
                        <th>Address</th>
                        <th>Gender</th>
                        <th>Roles</th> {/* Added Roles column */}
                    </tr>
                </thead>
                <tbody>
                    {users.map(user => (
                        <tr key={user.userId}>
                            <td>{user.userId}</td>
                            <td>{user.email}</td>
                            <td>{user.firstName}</td>
                            <td>{user.lastName}</td>
                            <td>{user.phoneNo}</td>
                            <td>{user.status}</td>
                            <td>{user.address}</td>
                            <td>{user.gender}</td>
                            <td>
                                {user.roles ? user.roles.map(role => role.roleName).join(', ') : 'N/A'} {/* Display roles */}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default UserListPage;
