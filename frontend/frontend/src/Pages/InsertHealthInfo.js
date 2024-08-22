import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './InsertHealthInfo.css'; 

const InsertHealthInfo = () => {
  const [formData, setFormData] = useState({
    height: '',
    weight: '',
    age: '',
    disease: '',
  });

  const [userId, setUserId] = useState(null);
  const [successMessage, setSuccessMessage] = useState('');
  const [hasHealthInfo, setHasHealthInfo] = useState(false);

  const predefinedDiseases = ['Diabetes', 'Blood Pressure', 'Heart Disease'];

  useEffect(() => {
    const storedUserId = localStorage.getItem('userId');
    if (storedUserId) {
      setUserId(parseInt(storedUserId));
      checkExistingHealthInfo(parseInt(storedUserId));
    }
  }, []);

  const checkExistingHealthInfo = async (userId) => {
    try {
      const response = await axios.get(`http://localhost:8080/users/user/getuserhealthinfosbyuserid/${userId}`);
      if (response.data) {
        setHasHealthInfo(true);
      }
    } catch (error) {
      console.error('Error checking existing health info:', error);
    }
  };

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!userId) {
      setSuccessMessage('Error: User ID not found. Please log in.');
      setTimeout(() => setSuccessMessage(''), 3000);
      return;
    }

    if (hasHealthInfo) {
      setSuccessMessage('You already have health information. Please update it instead.');
      setTimeout(() => setSuccessMessage(''), 3000);
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/users/user/insertuserhealthinfo', {
        height: parseFloat(formData.height),
        weight: parseFloat(formData.weight),
        age: parseInt(formData.age),
        userHealthInformation: formData.disease,
        userId: userId,
      });
      console.log('Health info submitted:', response.data);
      setSuccessMessage('Health information successfully inserted!');
      setFormData({ height: '', weight: '', age: '', disease: '' });
      setHasHealthInfo(true);
      setTimeout(() => setSuccessMessage(''), 3000);
    } catch (error) {
      console.error('Error submitting health info:', error);
      setSuccessMessage('Error inserting health information. Please try again.');
      setTimeout(() => setSuccessMessage(''), 3000);
    }
  };

  return (
    <div className="health-info-page">
      <div className="health-info-container">
        <h2>Insert Health Information</h2>
        {successMessage && <div className="success-message">{successMessage}</div>}
        {hasHealthInfo ? (
          <p>You already have health information. Please use the <a href="/healthinfo/update">update page</a> instead.</p>
        ) : (
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
            <div className="form-group">
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
            <div className="form-group">
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
            <div className="form-group">
              <label htmlFor="disease">Disease:</label>
              <input
                type="text"
                id="disease"
                name="disease"
                value={formData.disease}
                onChange={handleInputChange}
                list="diseaseOptions"
                required
              />
              <datalist id="diseaseOptions">
                {predefinedDiseases.map((disease) => (
                  <option key={disease} value={disease} />
                ))}
              </datalist>
            </div>
            <button type="submit">Submit Health Information</button>
          </form>
        )}
      </div>
    </div>
  );
};

export default InsertHealthInfo;
