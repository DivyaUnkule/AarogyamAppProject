import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const SignInPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState({});
  const [signInError, setSignInError] = useState('');
  const navigate = useNavigate();

  const validateForm = () => {
    const newErrors = {};

    if (!email) {
      newErrors.email = "Email can't be blank";
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      newErrors.email = 'Invalid email format';
    }

    if (!password) {
      newErrors.password = "Password can't be blank";
    } else if (password.length < 3 || password.length > 10) {
      newErrors.password = 'Password must be between 3 and 10 characters';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (validateForm()) {
      try {
        const response = await axios.post('http://localhost:8080/users/signin', { email, password });
        const { jwt, role, userId } = response.data;

        // Store JWT token and userId in local storage
        localStorage.setItem('token', jwt);
        localStorage.setItem('userId', userId);

        // Handle successful sign-in with role-based redirection
        if (role === 'ROLE_ADMIN') {
          navigate('/users/admin');
        } else if (role === 'ROLE_REGULARUSER') {
          navigate('/user/home');
        } else if (role === 'ROLE_WEIGHTLOSSUSER') {
          navigate('/weightloss/home');
        } else if (role === 'ROLE_WEIGHTGAINUSER') {
          navigate('/weightgain/home');
        } else {
          navigate('/'); // default or fallback route
        }

      } catch (error) {
        setSignInError('Invalid email or password');
        console.error('Sign in failed', error);
      }
    }
  };

  return (
    <Container className="mt-5">
      <h2 className="mb-4">Sign In</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3" controlId="formEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            isInvalid={!!errors.email}
          />
          <Form.Control.Feedback type="invalid">
            {errors.email}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group className="mb-3" controlId="formPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            isInvalid={!!errors.password}
          />
          <Form.Control.Feedback type="invalid">
            {errors.password}
          </Form.Control.Feedback>
        </Form.Group>

        {signInError && <Alert variant="danger">{signInError}</Alert>}

        <Button variant="primary" type="submit">
          Sign In
        </Button>
        <Button variant="primary" href="/register">
          Register
        </Button>
      </Form>
    </Container>
  );
};

export default SignInPage;
