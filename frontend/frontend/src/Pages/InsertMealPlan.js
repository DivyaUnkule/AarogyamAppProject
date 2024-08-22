import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';

const InsertMealPlan = () => {
  const [mealPlan, setMealPlan] = useState({
    name: '',
    description: '',
    amount: '',
    status: '',
    roleid: ''
  });
  const [message, setMessage] = useState('');
  const [isSuccess, setIsSuccess] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMealPlan(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setIsSuccess(false);

    try {
      const response = await axios.post('http://localhost:8080/users/admin/insertmealplan', {
        ...mealPlan,
        amount: parseFloat(mealPlan.amount),
        roleid: parseInt(mealPlan.roleid)
      });
      console.log('Server response:', response.data);
      setMessage('Meal plan inserted successfully!');
      setIsSuccess(true);
      // Reset form after successful insertion
      setMealPlan({
        name: '',
        description: '',
        amount: '',
        status: '',
        roleid: ''
      });
    } catch (error) {
      setMessage('Error inserting meal plan. Please try again.');
      console.error('Error inserting meal plan:', error.response || error);
    }
  };

  return (
    <Container>
      <h2>Insert Meal Plan</h2>
      {message && (
        <Alert variant={isSuccess ? 'success' : 'danger'}>
          {message}
        </Alert>
      )}
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>Name</Form.Label>
          <Form.Control type="text" name="name" value={mealPlan.name} onChange={handleChange} required />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Description</Form.Label>
          <Form.Control type="text" name="description" value={mealPlan.description} onChange={handleChange} required />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Amount</Form.Label>
          <Form.Control type="number" name="amount" value={mealPlan.amount} onChange={handleChange} required />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Status</Form.Label>
          <Form.Select name="status" value={mealPlan.status} onChange={handleChange} required>
            <option value="">Select Status</option>
            <option value="INACTIVE">INACTIVE</option>
            <option value="ACTIVE">ACTIVE</option>
          </Form.Select>
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Role ID</Form.Label>
          <Form.Control type="number" name="roleid" value={mealPlan.roleid} onChange={handleChange} required />
        </Form.Group>
        <Button variant="primary" type="submit">
          Insert Meal Plan
        </Button>
      </Form>
    </Container>
  );
};

export default InsertMealPlan;