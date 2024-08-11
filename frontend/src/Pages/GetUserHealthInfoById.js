import React, { useState } from 'react';
import axios from 'axios';
import { Container, Form, Button, Card, Alert } from 'react-bootstrap';

const GetUserHealthInfoById = () => {
    const [id, setId] = useState('');
    const [userHealthInfo, setUserHealthInfo] = useState(null);
    const [error, setError] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError('');  // Clear previous errors
        try {
            const response = await axios.get('http://localhost:8080/users/admin/getuserhealthinfobyId', {
                params: { id: id }
            });
            setUserHealthInfo(response.data);
        } catch (err) {
            setError('Failed to fetch data. Please check the ID and try again.');
        }
    };

    return (
        <Container>
            <h2 className="my-4">Get User Health Information</h2>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="formUserId">
                    <Form.Label>Enter User Health Info ID</Form.Label>
                    <Form.Control
                        type="number"
                        placeholder="Enter ID"
                        value={id}
                        onChange={(e) => setId(e.target.value)}
                    />
                </Form.Group>
                <Button variant="primary" type="submit" className="mt-3">
                    Fetch Information
                </Button>
            </Form>

            {error && <Alert variant="danger" className="mt-3">{error}</Alert>}

            {userHealthInfo && (
                <Card className="mt-4">
                    <Card.Header as="h5">User Health Information</Card.Header>
                    <Card.Body>
                        <Card.Title>ID: {userHealthInfo.id}</Card.Title>
                        <Card.Text><strong>User Health Information:</strong> {userHealthInfo.userHealthInformation}</Card.Text>
                        <Card.Text><strong>Height:</strong> {userHealthInfo.height} m</Card.Text>
                        <Card.Text><strong>Weight:</strong> {userHealthInfo.weight} kg</Card.Text>
                        <Card.Text><strong>Age:</strong> {userHealthInfo.age} years</Card.Text>
                        <Card.Text><strong>Created At:</strong> {new Date(userHealthInfo.createdAt).toLocaleString()}</Card.Text>
                        <Card.Text><strong>Updated At:</strong> {new Date(userHealthInfo.updatedAt).toLocaleString()}</Card.Text>

                        <Card.Header as="h6" className="mt-4">User Details</Card.Header>
                        <Card.Text><strong>Email:</strong> {userHealthInfo.user.email}</Card.Text>
                        <Card.Text><strong>First Name:</strong> {userHealthInfo.user.firstName}</Card.Text>
                        <Card.Text><strong>Last Name:</strong> {userHealthInfo.user.lastName}</Card.Text>
                        <Card.Text><strong>Phone No:</strong> {userHealthInfo.user.phoneNo}</Card.Text>
                        <Card.Text><strong>Status:</strong> {userHealthInfo.user.status}</Card.Text>
                        <Card.Text><strong>Address:</strong> {userHealthInfo.user.address}</Card.Text>
                        <Card.Text><strong>Gender:</strong> {userHealthInfo.user.gender}</Card.Text>
                    </Card.Body>
                </Card>
            )}
        </Container>
    );
};

export default GetUserHealthInfoById;
