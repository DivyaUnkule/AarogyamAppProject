import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';

const UpdateMealPlan = () => {
  const [mealPlan, setMealPlan] = useState({
    name: '',
    description: '',
    amount: '',
    status: '',
    roleid: '',
    mealid: ''
  });
  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMealPlan(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put('http://localhost:8080/users/admin/updatemealplan', {
        ...mealPlan,
        amount: parseFloat(mealPlan.amount),
        roleid: parseInt(mealPlan.roleid),
        mealid: parseInt(mealPlan.mealid)
      });
      setMessage('Meal plan updated successfully!');
      console.log('Updated meal plan:', response.data);
    } catch (error) {
      setMessage('Error updating meal plan. Please try again.');
      console.error('Error updating meal plan:', error);
    }
  };

  return (
    <Container>
      <h2>Update Meal Plan</h2>
      {message && <Alert variant={message.includes('successfully') ? 'success' : 'danger'}>{message}</Alert>}
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>Meal ID</Form.Label>
          <Form.Control type="number" name="mealid" value={mealPlan.mealid} onChange={handleChange} required />
        </Form.Group>
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
          Update Meal Plan
        </Button>
      </Form>
    </Container>
  );
};

export default UpdateMealPlan;