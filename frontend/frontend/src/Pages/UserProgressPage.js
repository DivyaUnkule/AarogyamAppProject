import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './UserProgressPage.css';

const UserProgressPage = () => {
  const [progress, setProgress] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProgress = async () => {
      try {
        const userId = localStorage.getItem('userId'); // Assuming you store userId in localStorage
        const token = localStorage.getItem('jwtToken'); // Assuming you store JWT token in localStorage

        if (!userId) {
          setError('User ID not found. Please log in.');
          return;
        }

        const response = await axios.get(`http://localhost:8080/users/getprogressbyid${userId}`,{
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });

        setProgress(response.data);
      } catch (err) {
        console.error('Error fetching progress:', err);
        setError('Failed to load progress. Please try again later.');
      }
    };

    fetchProgress();
  }, []);

  if (error) {
    return <div className="error-message">{error}</div>;
  }

  if (!progress) {
    return <div className="loading-message">Loading progress...</div>;
  }

  return (
    <div className="user-progress-page">
      <h1>Your Progress</h1>
      <div className="progress-card">
        <div className="progress-item">
          <span className="label">Progress ID:</span>
          <span className="value">{progress.progressId}</span>
        </div>
        <div className="progress-item">
          <span className="label">BMI:</span>
          <span className="value">{progress.bmi.toFixed(2)}</span>
        </div>
        <div className="progress-item">
          <span className="label">New Weight:</span>
          <span className="value">{progress.newWeight} kg</span>
        </div>
        <div className="progress-item">
          <span className="label">User ID:</span>
          <span className="value">{progress.userId}</span>
        </div>
      </div>
    </div>
  );
};

export default UserProgressPage;