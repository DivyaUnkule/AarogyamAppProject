import React from 'react';
import { Navbar, Nav, NavDropdown, Container } from 'react-bootstrap';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './AdminNavBar.css';

const AdminNavbar = () => {
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
    <div className="admin-page">
      <Navbar bg="primary" variant="dark" expand="lg" className="custom-navbar">
        <Container>
          <Navbar.Brand href="/users">Admin Dashboard</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <NavDropdown title="Water Intake" id="waterintake-dropdown">
                <NavDropdown.Item href="/admin/insertwaterintake">Insert Water Intake</NavDropdown.Item>
                <NavDropdown.Item href="/admin/getwaterintakes">View Water Intakes</NavDropdown.Item>
                <NavDropdown.Item href="/admin/updatewaterintake">Update Water Intake</NavDropdown.Item>
                <NavDropdown.Item href="/admin/deletewaterintake">Delete Water Intake</NavDropdown.Item>
                <NavDropdown.Item href="/admin/getwaterintakebyid">View Water Intake By Id</NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="Exercise & Yoga" id="exercise-dropdown">
                <NavDropdown.Item href="/admin/insertexercise">Insert Exercise</NavDropdown.Item>
                <NavDropdown.Item href="/admin/getexercises">View Exercises</NavDropdown.Item>
                <NavDropdown.Item href="/admin/getexercisebyId">View Exercise By Id</NavDropdown.Item>
                <NavDropdown.Item href="/admin/updateexercise">Update Exercise</NavDropdown.Item>
                <NavDropdown.Item href="/admin/deleteexercise">Delete Exercise</NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="User Management" id="user-dropdown">
                <NavDropdown.Item href="/admin/addUser">Add User</NavDropdown.Item>
                <NavDropdown.Item href="/admin/getAllUsers">View All Users</NavDropdown.Item>
                <NavDropdown.Item href="/admin/getUserById">View User By Id</NavDropdown.Item>
                <NavDropdown.Item href="/admin/updateUserDetails">Update User</NavDropdown.Item>
                <NavDropdown.Item href="/admin/deleteUserById">Delete User</NavDropdown.Item>
                <NavDropdown.Item href="/user/{userId}/image_upload">Upload Profile Picture</NavDropdown.Item>
                <NavDropdown.Item href="/user/{userId}/image">View Profile Picture</NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="User Health Info" id="health-info-dropdown">
                <NavDropdown.Item href="/admin/getuserhealthinfos">View Health Infos</NavDropdown.Item>
                <NavDropdown.Item href="/admin/getUserById">View Health Info By Id</NavDropdown.Item>
              </NavDropdown>    
              <NavDropdown title="Meal Plans" id="mealplan-dropdown">
                <NavDropdown.Item href="/admin/insertmealplan">Insert Meal Plan</NavDropdown.Item>
                <NavDropdown.Item href="/admin/getallmealplan">View All Meal Plans</NavDropdown.Item>
                <NavDropdown.Item href="/admin/updatemealplan">Update Meal Plan</NavDropdown.Item>
                <NavDropdown.Item href="/admin/deletemealplan">Delete Meal Plan</NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="Articles" id="article-dropdown">
                <NavDropdown.Item href="/admin/insertarticle">Insert Article</NavDropdown.Item>
                <NavDropdown.Item href="/admin/getallarticles">View All Articles</NavDropdown.Item>
                <NavDropdown.Item href="/admin/updatearticle">Update Article</NavDropdown.Item>
                <NavDropdown.Item href="/admin/deletearticle">Delete Article</NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="Progress" id="progress-dropdown">
                <NavDropdown.Item href="/allprogress">View All User Progress</NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="UserPayment" id="payment-dropdown">
                <NavDropdown.Item href="/adminpayment">View All User Payment Status</NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="Profile" id="user-dropdown">
                <NavDropdown.Item href="/user/{userId}/image_upload">Edit Profile Picture</NavDropdown.Item>
                <NavDropdown.Item href="/user/{userId}/image">View Profile Picture</NavDropdown.Item>
              </NavDropdown>
            </Nav>
            <Nav>
              <NavDropdown title="Logout" id="basic-nav-dropdown" align="end">
                <NavDropdown.Item onClick={handleLogout}>Logout</NavDropdown.Item>
              </NavDropdown>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <div className="welcome-container">
      <div className="welcome-message">
        <h2>Welcome, Admin!</h2>
        <p>Manage your application with ease using the navigation menu above.</p>
      </div>
    </div>
  </div>
);
};

export default AdminNavbar;