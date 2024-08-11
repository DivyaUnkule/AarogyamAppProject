import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';

const ExerciseYogaUpdate = () => {
  const [id, setId] = useState('');
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [roleid, setRoleId] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setSuccess(null);

    try {
      // Send the request to the backend with proper data types
      const response = await axios.put('http://localhost:8080/users/admin/updateexercise', {
        id: Number(id), // Ensure id is a number
        name,
        description,
        roleid: Number(roleid) // Ensure roleid is a number
      });

      console.log('Response:', response.data); // Log the response for debugging
      setSuccess('Exercise updated successfully');
    } catch (err) {
      console.error('Error details:', err.response ? err.response.data : err.message); // Log the error for debugging
      setError('Failed to update exercise data');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="mt-4">
      <h2 className="mb-4">Update Exercise Details</h2>

      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="exerciseId">
          <Form.Label>Exercise ID</Form.Label>
          <Form.Control
            type="number"
            placeholder="Enter ID"
            value={id}
            onChange={(e) => setId(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group controlId="exerciseName" className="mt-3">
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group controlId="exerciseDescription" className="mt-3">
          <Form.Label>Description</Form.Label>
          <Form.Control
            as="textarea"
            rows={3}
            placeholder="Enter description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group controlId="exerciseRoleId" className="mt-3">
          <Form.Label>Role ID</Form.Label>
          <Form.Control
            type="number"
            placeholder="Enter role ID"
            value={roleid}
            onChange={(e) => setRoleId(e.target.value)}
            required
          />
        </Form.Group>

        <Button variant="primary" type="submit" className="mt-3" disabled={loading}>
          {loading ? 'Updating...' : 'Update Exercise'}
        </Button>
      </Form>

      {success && <Alert variant="success" className="mt-4">{success}</Alert>}
      {error && <Alert variant="danger" className="mt-4">{error}</Alert>}
    </Container>
  );
};

export default ExerciseYogaUpdate;
