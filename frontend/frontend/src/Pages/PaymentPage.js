import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert, Card } from 'react-bootstrap';
import './PaymentPage.css';

const PaymentPage = () => {
  const [email, setEmail] = useState('');
  const [paymentMode, setPaymentMode] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setMessage('');

    try {
      const response = await axios.post('http://localhost:8080/users/user/makePayment', {
        email,
        paymentMode
      });
      setMessage('Payment successful! You now have access to premium features.');
      setEmail('');
      setPaymentMode('');
    } catch (error) {
      if (error.response) {
        setError(error.response.data || 'An error occurred. Please try again.');
      } else if (error.request) {
        setError('No response received from server. Please try again.');
      } else {
        setError('An error occurred. Please try again.');
      }
    }
  };

  return (
    <Container className="payment-container">
      <Card className="payment-card">
        <Card.Body>
          <h2 className="payment-title">Unlock Premium Features</h2>
          <p className="payment-description">
            Pay 2000RS to unlock access to our expert trainers and exclusive meal plans!
          </p>
          {message && <Alert variant="success">{message}</Alert>}
          {error && <Alert variant="danger">{error}</Alert>}
          <Form onSubmit={handleSubmit} className="payment-form">
            <Form.Group className="mb-3" controlId="formEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                type="email"
                placeholder="Enter email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                className="payment-input"
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formPaymentMode">
              <Form.Label>Payment Mode</Form.Label>
              <Form.Select
                value={paymentMode}
                onChange={(e) => setPaymentMode(e.target.value)}
                required
                className="payment-input"
              >
                <option value="">Select payment mode</option>
                <option value="CREDIT_CARD">Credit Card</option>
                <option value="DEBIT_CARD">Debit Card</option>
                <option value="NET_BANKING">Net Banking</option>
                <option value="UPI">UPI</option>
              </Form.Select>
            </Form.Group>

            <Button variant="primary" type="submit" className="payment-button">
              Make Payment
            </Button>
          </Form>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default PaymentPage;