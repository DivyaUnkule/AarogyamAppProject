import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import './UpdateHealthInfo.css';

const UpdateHealthInfo = ({ userId }) => {
  const [formData, setFormData] = useState({
    id: '',
    height: '',
    weight: '',
    age: '',
    userHealthInformation: '',
  });
  const [updateMessage, setUpdateMessage] = useState('');
  const predefinedDiseases = ['Diabetes', 'Blood Pressure', 'Heart Disease'];

  const fetchHealthInfo = useCallback(async () => {
    try {
      const response = await axios.get(`http://localhost:8080/users/user/getuserhealthinfosbyuserid/${userId}`);
      setFormData(response.data);
    } catch (error) {
      console.error('Error fetching health info:', error);
    }
  }, [userId]);

  useEffect(() => {
    fetchHealthInfo();
  }, [fetchHealthInfo]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put('http://localhost:8080/users/user/updateuserhealthinfo', {
        id: formData.id,
        height: parseFloat(formData.height),
        weight: parseFloat(formData.weight),
        age: parseInt(formData.age),
        userHealthInformation: formData.userHealthInformation,
      });
      console.log('Health info updated:', response.data);
      setUpdateMessage('Health information updated successfully!');
      setTimeout(() => setUpdateMessage(''), 3000);
    } catch (error) {
      console.error('Error updating health info:', error);
      setUpdateMessage('Error updating health information. Please try again.');
      setTimeout(() => setUpdateMessage(''), 3000);
    }
  };

  return (
    <div className="update-health-info-container">
      <div className="update-health-info">
        <h2>Update Health Information</h2>
        {updateMessage && <div className="update-message">{updateMessage}</div>}
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="height">Height (in meters):</label>
            <input
              type="number"
              id="height"
              name="height"
              value={formData.height}
              onChange={handleInputChange}
              required
            />
          </div>
          <div>
            <label htmlFor="weight">Weight (in kg):</label>
            <input
              type="number"
              id="weight"
              name="weight"
              value={formData.weight}
              onChange={handleInputChange}
              required
            />
          </div>
          <div>
            <label htmlFor="age">Age:</label>
            <input
              type="number"
              id="age"
              name="age"
              value={formData.age}
              onChange={handleInputChange}
              required
            />
          </div>
          <div>
            <label htmlFor="userHealthInformation">Health Condition:</label>
            <select
              id="userHealthInformationDropdown"
              onChange={(e) => {
                if (e.target.value !== "") {
                  setFormData({ ...formData, userHealthInformation: e.target.value });
                }
              }}
              value={predefinedDiseases.includes(formData.userHealthInformation) ? formData.userHealthInformation : ""}
            >
              <option value="">Select or type below</option>
              {predefinedDiseases.map((disease) => (
                <option key={disease} value={disease}>
                  {disease}
                </option>
              ))}
            </select>
          </div>
          <div>
            <label htmlFor="userHealthInformation">Or specify health condition:</label>
            <input
              type="text"
              id="userHealthInformation"
              name="userHealthInformation"
              value={formData.userHealthInformation}
              onChange={handleInputChange}
              placeholder="Enter health condition"
              required
            />
          </div>
          <button type="submit">Update Health Information</button>
        </form>
      </div>
    </div>
  );
};

export default UpdateHealthInfo;