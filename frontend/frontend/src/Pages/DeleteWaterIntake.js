import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';

const DeleteWaterIntake = () => {
  const [id, setId] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setSuccess(null);

    try {
      const response = await axios.delete('http://localhost:8080/users/admin/deletewaterintake', {
        params: { id } // Pass ID as query parameter
      });

      if (response.status === 200) {
        setSuccess('Water intake record deleted successfully');
        setId('');
      } else {
        setError('Unexpected response status');
      }
    } catch (err) {
      console.error('Error details:', err.response ? err.response.data : err.message);
      setError('Failed to delete water intake record');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="mt-4">
      <h2 className="mb-4">Delete Water Intake Record</h2>

      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="waterIntakeId">
          <Form.Label>Water Intake ID</Form.Label>
          <Form.Control
            type="number"
            placeholder="Enter Water Intake ID"
            value={id}
            onChange={(e) => setId(e.target.value)}
            required
          />
        </Form.Group>

        <Button variant="danger" type="submit" className="mt-3" disabled={loading}>
          {loading ? 'Deleting...' : 'Delete Water Intake'}
        </Button>
      </Form>

      {success && <Alert variant="success" className="mt-4">{success}</Alert>}
      {error && <Alert variant="danger" className="mt-4">{error}</Alert>}
    </Container>
  );
};

export default DeleteWaterIntake;
