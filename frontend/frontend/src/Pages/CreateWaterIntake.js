import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Row, Col, Alert } from 'react-bootstrap';

const CreateWaterIntake = () => {
  const [waterIntake, setWaterIntake] = useState({
    information: '',
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setWaterIntake(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Send data to the API
    axios.post('http://localhost:8080/users/admin/insertwaterintake', waterIntake) // Replace with your actual API endpoint
      .then(response => {
        setSuccess('Water Intake created successfully');
        setWaterIntake({
          information: '',
        });
      })
      .catch(error => {
        setError('Error creating Water Intake');
        console.error("There was an error creating the Water Intake!", error);
      });
  };

  return (
    <Container>
      <Row className="justify-content-md-center">
        <Col md={6}>
          <h2 className="mt-4">Create Water Intake</h2>
          {success && <Alert variant="success">{success}</Alert>}
          {error && <Alert variant="danger">{error}</Alert>}
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formInformation">
              <Form.Label>Information</Form.Label>
              <Form.Control
                type="text"
                name="information"
                value={waterIntake.information}
                onChange={handleChange}
                placeholder="Enter water intake information"
                required
              />
            </Form.Group>

            <Button variant="primary" type="submit" className="mt-4">
              Create Water Intake
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default CreateWaterIntake;
