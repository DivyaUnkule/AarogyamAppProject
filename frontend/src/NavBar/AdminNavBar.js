import React from 'react';
import { Navbar, Nav, NavDropdown, Button } from 'react-bootstrap';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AdminNavbar = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Perform logout action using axios
    axios.post('http://localhost:8080/users/logout')
      .then(response => {
        // Clear local storage or any other client-side data if needed
        localStorage.removeItem('userId');
        localStorage.removeItem('token');
        
        // Redirect to the sign-in page
        navigate('/');
      })
      .catch(error => {
        console.error("There was an error logging out!", error);
      });
  };

  return (
    <Navbar bg="dark" variant="dark" expand="lg">
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
            
          </NavDropdown>
          <NavDropdown title="User Health Info" id="health-info-dropdown">
            <NavDropdown.Item href="/admin/getuserhealthinfos">View Health Infos</NavDropdown.Item>
            <NavDropdown.Item href="/admin/getUserById">View Health Info By Id</NavDropdown.Item>
          </NavDropdown>

          <NavDropdown title="Profile" id="user-dropdown">
            <NavDropdown.Item href="/{Id}/image_upload">Edit Profile Picture</NavDropdown.Item>
            <NavDropdown.Item href="/{userId}/image"> View Profile Picture</NavDropdown.Item>

          </NavDropdown>
        </Nav>
        <Nav className="ms-auto">
          <Button variant="outline-light" onClick={handleLogout}>Logout</Button>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default AdminNavbar;
