import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table, Container, Alert } from 'react-bootstrap';

const GetAllMealPlans = () => {
  const [mealPlans, setMealPlans] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchMealPlans();
  }, []);

  const fetchMealPlans = async () => {
    try {
      const response = await axios.get('http://localhost:8080/users/admin/getallmealplan');
      setMealPlans(response.data);
    } catch (err) {
      setError('Error fetching meal plans. Please try again later.');
      console.error('Error fetching meal plans:', err);
    }
  };

  return (
    <Container>
      <h2>All Meal Plans</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Role</th>
          </tr>
        </thead>
        <tbody>
          {mealPlans.map((plan) => (
            <tr key={plan.id}>
              <td>{plan.id}</td>
              <td>{plan.name}</td>
              <td>{plan.description}</td>
              <td>{plan.amount}</td>
              <td>{plan.status}</td>
              <td>{plan.role.roleName}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default GetAllMealPlans;