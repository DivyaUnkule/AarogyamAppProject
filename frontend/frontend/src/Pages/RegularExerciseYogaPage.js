import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ExerciseYogaPage.css';

const RegularExerciseYogaPage = () => {
  const [exercises, setExercises] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/users/regularuser/exercise')
      .then(response => {
        setExercises(response.data);
      })
      .catch(error => {
        console.error("Error fetching exercises and yoga:", error);
      });
  }, []);

  return (
    <div className="exercise-yoga-page">
      <div className="exercise-yoga-container">
        <h1>Exercise and Yoga</h1>
        {exercises.map((item, index) => (
          <div key={index} className="exercise-item">
            <h2>{item.name}</h2>
            <p>{item.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default RegularExerciseYogaPage;