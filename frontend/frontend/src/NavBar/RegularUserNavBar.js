import React from 'react';
import { Navbar, Nav, NavDropdown, Container } from 'react-bootstrap';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './RegularUserNavBar.css';

const RegularUserNavbar = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    axios.post('http://localhost:8080/users/user/logout')
      .then(response => {
        // Clear any stored user data (e.g., tokens, user info)
        localStorage.removeItem('jwtToken');
        localStorage.removeItem('userId');
        localStorage.removeItem('userRole');
        
        console.log(response.data); // Log the response message
        navigate('/');
      })
      .catch(error => {
        console.error("There was an error logging out!", error);
        // You might want to show an error message to the user here
      });
  };

  return (
    <>
      <Navbar expand="lg">
        <Container>
          <Navbar.Brand href="/dashboard">WellnessHub</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="/userprofile">Profile</Nav.Link>
              <Nav.Link href="/mealplan">Meal Plan</Nav.Link>
              <Nav.Link href="/waterintake">Water Intake</Nav.Link>
              <Nav.Link href="/exercises">Exercises</Nav.Link>
              <Nav.Link href="/articles">Articles</Nav.Link>
              <NavDropdown title="Health Info" id="health-info-dropdown">
                <NavDropdown.Item href="/healthinfo/insert">Insert Health Info</NavDropdown.Item>
                <NavDropdown.Item href="/healthinfo/view">View Health Info</NavDropdown.Item>
                <NavDropdown.Item href="/healthinfo/update">Update Health Info</NavDropdown.Item>        
              </NavDropdown>
              <Nav.Link href="/progress">Progress</Nav.Link>
              <Nav.Link href="/payment">Extra Features</Nav.Link>
              <NavDropdown title="Profile" id="user-dropdown">
                <NavDropdown.Item href="/{Id}/image_upload">Edit Profile Picture</NavDropdown.Item>
                <NavDropdown.Item href="/{userId}/image">View Profile Picture</NavDropdown.Item>
              </NavDropdown>
            </Nav>
            <Nav>
              <NavDropdown title="Logout" id="basic-nav-dropdown">
                <NavDropdown.Item onClick={handleLogout}>Logout</NavDropdown.Item>
              </NavDropdown>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <div className="welcome-banner">
        <h1 className="welcome-title">Welcome to Your Wellness Journey!</h1>
        <p className="welcome-message">
          Embark on a path to better health and vitality. Our personalized tools and resources 
          are here to support you every step of the way. Let's make wellness a way of life!
        </p>
      </div>
    </>
  );
};

export default RegularUserNavbar;