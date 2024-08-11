import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';

const WaterIntakeUpdate = () => {
  const [waterIntakeId, setWaterIntakeId] = useState('');
  const [information, setInformation] = useState('');
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
      const response = await axios.put('http://localhost:8080/users/admin/updatewaterintake', {
        waterIntakeId: Number(waterIntakeId), // Ensure waterIntakeId is a number
        information,
      });

      console.log('Response:', response.data); // Log the response for debugging
      setSuccess('Water Intake updated successfully');
    } catch (err) {
      console.error('Error details:', err.response ? err.response.data : err.message); // Log the error for debugging
      setError('Failed to update water intake data');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="mt-4">
      <h2 className="mb-4">Update Water Intake Details</h2>

      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="waterIntakeId">
          <Form.Label>Water Intake ID</Form.Label>
          <Form.Control
            type="number"
            placeholder="Enter Water Intake ID"
            value={waterIntakeId}
            onChange={(e) => setWaterIntakeId(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group controlId="information" className="mt-3">
          <Form.Label>Information</Form.Label>
          <Form.Control
            as="textarea"
            rows={3}
            placeholder="Enter information"
            value={information}
            onChange={(e) => setInformation(e.target.value)}
            required
          />
        </Form.Group>

        <Button variant="primary" type="submit" className="mt-3" disabled={loading}>
          {loading ? 'Updating...' : 'Update Water Intake'}
        </Button>
      </Form>

      {success && <Alert variant="success" className="mt-4">{success}</Alert>}
      {error && <Alert variant="danger" className="mt-4">{error}</Alert>}
    </Container>
  );
};

export default WaterIntakeUpdate;
