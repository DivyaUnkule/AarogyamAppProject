import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import './UserProfile.css';

const UserProfile = ({ userId }) => {
  const [user, setUser] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [error, setError] = useState(null);

  const fetchUserData = useCallback(async () => {
    try {
      if (!userId) {
        throw new Error("User ID not found. Please log in again.");
      }
      const token = localStorage.getItem('jwtToken');
      const response = await axios.get(`http://localhost:8080/users/user/getUser/${userId}`, {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      setUser(response.data);
    } catch (error) {
      console.error('Error fetching user data:', error);
      setError(error.message || "Failed to fetch user data. Please try again.");
    }
  }, [userId]);

  useEffect(() => {
    fetchUserData();
  }, [fetchUserData]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUser(prevUser => ({ ...prevUser, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem('jwtToken');
      await axios.put(`http://localhost:8080/users/updateOwnDetails`, user, {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      setIsEditing(false);
      await fetchUserData();
    } catch (error) {
      console.error('Error updating user data:', error);
      setError("Failed to update user data. Please try again.");
    }
  };

  if (error) return <div className="error-message">{error}</div>;
  if (!user) return <div className="loading-message">Loading...</div>;

  const renderField = (label, value) => (
    <p><strong>{label}:</strong> {value}</p>
  );

  return (
    <div className="user-profile">
      <h2>User Profile</h2>
      {isEditing ? (
        <form onSubmit={handleSubmit}>
          <div>
            <label>User ID: </label>
            <input name="id" value={user.userId} readOnly />
          </div>
          <div>
            <label>Email: </label>
            <input name="email" value={user.email} onChange={handleInputChange} placeholder="Email" />
          </div>
          <div>
            <label>First Name: </label>
            <input name="firstName" value={user.firstName} onChange={handleInputChange} placeholder="First Name" />
          </div>
          <div>
            <label>Last Name: </label>
            <input name="lastName" value={user.lastName} onChange={handleInputChange} placeholder="Last Name" />
          </div>
          <div>
            <label>Phone Number: </label>
            <input name="phoneNo" value={user.phoneNo} onChange={handleInputChange} placeholder="Phone Number" />
          </div>
          <div>
            <label>Address: </label>
            <input name="address" value={user.address} onChange={handleInputChange} placeholder="Address" />
          </div>
          <div>
            <label>Gender: </label>
            <select name="gender" value={user.gender} onChange={handleInputChange}>
              <option value="MALE">Male</option>
              <option value="FEMALE">Female</option>
              <option value="OTHER">Other</option>
            </select>
          </div>
          <div>
            <label>Status: </label>
            <input name="status" value={user.status} readOnly disabled />
          </div>
          <div className="form-actions">
            <button type="submit">Save Changes</button>
            <button type="button" onClick={() => setIsEditing(false)}>Cancel</button>
          </div>
        </form>
      ) : (
        <div className="user-info">
          {renderField("ID", user.userId)}
          {renderField("Email", user.email)}
          {renderField("Name", `${user.firstName} ${user.lastName}`)}
          {renderField("Phone", user.phoneNo)}
          {renderField("Address", user.address)}
          {renderField("Gender", user.gender)}
          {renderField("Status", user.status)}
          {user.profilePicPath && renderField("Profile Picture", user.profilePicPath)}
          <button onClick={() => setIsEditing(true)}>Edit Profile</button>
        </div>
      )}
    </div>
  );
};

export default UserProfile;