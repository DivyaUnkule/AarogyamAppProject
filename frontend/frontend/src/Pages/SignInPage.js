import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import './SignInPage.css';
import { jwtDecode } from 'jwt-decode';

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
        console.log('Sign-in response:', response.data);
      
        const { jwt, role } = response.data;
        
        // Decode and log the entire JWT payload
        const decodedToken = jwtDecode(jwt);
        console.log('Decoded JWT payload:', decodedToken);
      
        // Now extract the user ID using the correct field name
        const userId = decodedToken.user_id; // Adjust this if needed
      
        localStorage.setItem('userId', userId);
        localStorage.setItem('jwtToken', jwt);
        localStorage.setItem('userRole', role);
      
        console.log('Stored userId:', userId);
        console.log('Stored jwtToken:', jwt);
        console.log('Stored userRole:', role);
      
        // Redirect to the appropriate page based on the role
        // history.push('/meal-plan');
      
        // Handle successful sign-in with role-based redirection
        if (role === 'ROLE_ADMIN') {
          navigate('/users/admin');
        } else if (role === 'ROLE_REGULARUSER') {
          navigate('/regularuser/home');
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
    <Container className="signin-container">
      <div className="signin-card">
        <h2 className="signin-title">Sign In</h2>
        <Form onSubmit={handleSubmit} className="signin-form">
          <Form.Group className="mb-3" controlId="formEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              isInvalid={!!errors.email}
              className="signin-input"
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
              className="signin-input"
            />
            <Form.Control.Feedback type="invalid">
              {errors.password}
            </Form.Control.Feedback>
          </Form.Group>
          {signInError && <Alert variant="danger">{signInError}</Alert>}

          <Button variant="primary" type="submit" className="signin-button">
            Sign In
          </Button>
          <Button variant="outline-secondary" href="/register" className="register-button">
            Register
          </Button>

          <div className="message-container">
            <p className="signin-message">
              Not registered yet? <a href="/register">Click here to register</a>.
            </p>
            <p className="signin-message">
              <a href="/forgotpassword">Forgot your password?</a>
            </p>
          </div>
        </Form>
      </div>
    </Container>
  );
};

export default SignInPage;
