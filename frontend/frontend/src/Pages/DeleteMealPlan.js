import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';

const DeleteMealPlan = () => {
  const [mealId, setMealId] = useState('');
  const [message, setMessage] = useState('');
  const [isSuccess, setIsSuccess] = useState(false);

  const handleChange = (e) => {
    setMealId(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setIsSuccess(false);

    try {
      await axios.delete(`http://localhost:8080/users/admin/deletemealplan/${mealId}`);
      setMessage('Meal plan deleted successfully!');
      setIsSuccess(true);
      setMealId(''); // Clear the input after successful deletion
    } catch (error) {
      setMessage('Error deleting meal plan. Please try again.');
      console.error('Error deleting meal plan:', error);
    }
  };

  return (
    <Container className="mt-4">
      <h2>Delete Meal Plan</h2>
      {message && (
        <Alert variant={isSuccess ? 'success' : 'danger'}>
          {message}
        </Alert>
      )}
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>Meal Plan ID</Form.Label>
          <Form.Control
            type="number"
            value={mealId}
            onChange={handleChange}
            required
            min="1"
          />
        </Form.Group>
        <Button variant="danger" type="submit">
          Delete Meal Plan
        </Button>
      </Form>
    </Container>
  );
};

export default DeleteMealPlan;