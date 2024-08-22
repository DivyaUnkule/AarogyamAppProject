import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './AdminAllUserProgressPage.css';

const AdminAllUserProgressPage = () => {
  const [progressList, setProgressList] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchAllUserProgress = async () => {
      try {
        const token = localStorage.getItem('jwtToken'); // Assuming you store JWT token in localStorage
        const response = await axios.get('http://localhost:8080/users/admin/getalluserprogress', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        setProgressList(response.data);
      } catch (err) {
        console.error('Error fetching all user progress:', err);
        setError('Failed to load user progress. Please try again later.');
      }
    };
    fetchAllUserProgress();
  }, []);

  if (error) {
    return <div className="error-message">{error}</div>;
  }

  return (
    <div className="admin-all-user-progress-page">
      <h1>All User Progress</h1>
      {progressList.length === 0 ? (
        <p>No user progress data available.</p>
      ) : (
        <table className="progress-table">
          <thead>
            <tr>
              <th>Progress ID</th>
              <th>BMI</th>
              <th>New Weight (kg)</th>
              <th>User ID</th>
            </tr>
          </thead>
          <tbody>
            {progressList.map((progress) => (
              <tr key={progress.progressId}>
                <td>{progress.progressId}</td>
                <td>{progress.bmi.toFixed(2)}</td>
                <td>{progress.newWeight}</td>
                <td>{progress.userId}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default AdminAllUserProgressPage;