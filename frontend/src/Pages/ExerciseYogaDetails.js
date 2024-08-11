import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';

const ExerciseYogaDetails = () => {
  const [id, setId] = useState('');
  const [exercise, setExercise] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleInputChange = (e) => {
    setId(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const response = await axios.get(`http://localhost:8080/users/admin/getexercisebyId`, {
        params: { id },
      });
      setExercise(response.data);
    } catch (err) {
      setError('Failed to fetch exercise data');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="mt-4">
      <h2 className="mb-4">View Exercise Details</h2>

      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="exerciseId">
          <Form.Label>Enter Exercise ID</Form.Label>
          <Form.Control
            type="number"
            placeholder="Enter ID"
            value={id}
            onChange={handleInputChange}
            required
          />
        </Form.Group>
        <Button variant="primary" type="submit" className="mt-3">
          Fetch Exercise
        </Button>
      </Form>

      {loading && <div className="text-center mt-4">Loading...</div>}
      {error && <Alert variant="danger" className="mt-4">{error}</Alert>}
      {exercise && (
        <div className="card mt-4">
          <div className="card-header bg-primary text-white">
            <h3>{exercise.name}</h3>
          </div>
          <div className="card-body">
            <p><strong>Description:</strong> {exercise.description}</p>
            <p><strong>ID:</strong> {exercise.id}</p>
            <p><strong>Role ID:</strong> {exercise.role.id}</p>
            <p><strong>Role Name:</strong> {exercise.role.roleName}</p>
          </div>
        </div>
      )}
    </Container>
  );
};

export default ExerciseYogaDetails;
