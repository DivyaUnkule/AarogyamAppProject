import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table, Container, Alert } from 'react-bootstrap';
import './AdminPaymentsPage.css';

const AdminPaymentsPage = () => {
  const [payments, setPayments] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchPayments();
  }, []);

  const fetchPayments = async () => {
    try {
      const response = await axios.get('http://localhost:8080/users/user/admin/getAllpayments');
      setPayments(response.data);
    } catch (error) {
      setError('Failed to fetch payments. Please try again later.');
      console.error('Error fetching payments:', error);
    }
  };

  return (
    <Container className="admin-payments-container">
      <h2 className="admin-payments-title">User Payments</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <Table striped bordered hover className="admin-payments-table">
        <thead>
          <tr>
            <th>Payment ID</th>
            <th>User Email</th>
            <th>Payment Mode</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {payments.map((payment) => (
            <tr key={payment.paymentId}>
              <td>{payment.paymentId}</td>
              <td>{payment.userEmail}</td>
              <td>{payment.paymentMode}</td>
              <td>Rs{payment.amount.toFixed(2)}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default AdminPaymentsPage;