import React, { useState, useEffect } from 'react';
import axios from 'axios';

const WaterIntakeList = () => {
  const [waterIntakes, setWaterIntakes] = useState([]);

  useEffect(() => {
    const fetchWaterIntakes = async () => {
      try {
        const response = await axios.get('http://localhost:8080/users/admin/getwaterintakes');
        setWaterIntakes(response.data);
      } catch (error) {
        console.error('Error fetching water intake records:', error);
      }
    };

    fetchWaterIntakes();
  }, []);

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Water Intake Records</h1>
      <div className="row">
        {waterIntakes.map((waterIntake) => (
          <div key={waterIntake.waterIntakeId} className="col-md-4 mb-4">
            <div className="card h-100">
              <div className="card-body">
                <h3 className="card-title">Water Intake ID: {waterIntake.waterIntakeId}</h3>
                <p className="card-text">{waterIntake.information}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default WaterIntakeList;
