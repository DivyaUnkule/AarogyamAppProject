import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Row, Col, Alert } from 'react-bootstrap';

const CreateExerciseYoga = () => {
  const [exerciseYoga, setExerciseYoga] = useState({
    name: '',
    description: '',
    roleid: ''
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setExerciseYoga(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Send data to the API
    axios.post('http://localhost:8080/users/admin/insertexercise', exerciseYoga) // Replace with your actual API endpoint
      .then(response => {
        setSuccess('Exercise Yoga created successfully');
        setExerciseYoga({
          name: '',
          description: '',
          roleid: ''
        });
      })
      .catch(error => {
        setError('Error creating Exercise Yoga');
        console.error("There was an error creating the Exercise Yoga!", error);
      });
  };

  return (
    <Container>
      <Row className="justify-content-md-center">
        <Col md={6}>
          <h2 className="mt-4">Create Exercise & Yoga</h2>
          {success && <Alert variant="success">{success}</Alert>}
          {error && <Alert variant="danger">{error}</Alert>}
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formName">
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                name="name"
                value={exerciseYoga.name}
                onChange={handleChange}
                placeholder="Enter name"
                required
              />
            </Form.Group>

            <Form.Group controlId="formDescription" className="mt-3">
              <Form.Label>Description</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                name="description"
                value={exerciseYoga.description}
                onChange={handleChange}
                placeholder="Enter description"
                required
              />
            </Form.Group>

            <Form.Group controlId="formRoleId" className="mt-3">
              <Form.Label>Role ID</Form.Label>
              <Form.Control
                type="number"
                name="roleid"
                value={exerciseYoga.roleid}
                onChange={handleChange}
                placeholder="Enter role ID"
                required
              />
            </Form.Group>

            <Button variant="primary" type="submit" className="mt-4">
              Create Exercise Yoga
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default CreateExerciseYoga;
