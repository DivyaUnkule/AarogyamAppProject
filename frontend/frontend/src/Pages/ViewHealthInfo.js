import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import './ViewHealthInfo.css';

const ViewHealthInfo = ({ userId }) => {
  const [healthInfo, setHealthInfo] = useState(null);

  const fetchUserHealthInfo = useCallback(async () => {
    try {
      const response = await axios.get(`http://localhost:8080/users/user/getuserhealthinfosbyuserid/${userId}`);
      setHealthInfo(response.data);
    } catch (error) {
      console.error('Error fetching health info:', error);
    }
  }, [userId]);

  useEffect(() => {
    fetchUserHealthInfo();
  }, [fetchUserHealthInfo]);

  if (!healthInfo) return <div className="loading">Loading...</div>;

  return (
    <div className="health-info-container">
      <h2 className="health-info-title">User Health Information</h2>
      <div className="health-info-item">
        <span className="health-info-label">Height:</span>
        <span className="health-info-value">{healthInfo.height}</span>
      </div>
      <div className="health-info-item">
        <span className="health-info-label">Weight:</span>
        <span className="health-info-value">{healthInfo.weight}</span>
      </div>
      <div className="health-info-item">
        <span className="health-info-label">Age:</span>
        <span className="health-info-value">{healthInfo.age}</span>
      </div>
      <div className="health-info-item">
        <span className="health-info-label">Health Condition:</span>
        <span className="health-info-value">{healthInfo.userHealthInformation}</span>
      </div>
    </div>
  );
};

export default ViewHealthInfo;