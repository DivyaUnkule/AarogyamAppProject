import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ExerciseYogaList = () => {
  const [exercises, setExercises] = useState([]);

  useEffect(() => {
    const fetchExercises = async () => {
      try {
        const response = await axios.get('http://localhost:8080/users/admin/getexercises');
        setExercises(response.data);
      } catch (error) {
        console.error('Error fetching exercises:', error);
      }
    };

    fetchExercises();
  }, []);

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Yoga Exercises</h1>
      <div className="row">
        {exercises.map((exercise) => (
          <div key={exercise.id} className="col-md-4 mb-4">
            <div className="card h-100">
              <div className="card-body">
                <h3 className="card-title">{exercise.name}</h3>
                <p className="card-text">{exercise.description}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ExerciseYogaList;