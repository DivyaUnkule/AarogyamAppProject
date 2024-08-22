import React, { useState } from 'react';
import axios from 'axios';
import { Container, Form, Button, Card, Alert } from 'react-bootstrap';

const GetWaterIntakeById = () => {
    const [id, setId] = useState('');
    const [waterIntake, setWaterIntake] = useState(null);
    const [error, setError] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError('');  // Clear previous errors
        try {
            const response = await axios.get('http://localhost:8080/users/admin/getwaterintakebyid', {
                params: { id: id }
            });
            setWaterIntake(response.data);
        } catch (err) {
            setError('Failed to fetch data. Please check the ID and try again.');
        }
    };

    return (
        <Container>
            <h2 className="my-4">Get Water Intake Information</h2>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="formWaterIntakeId">
                    <Form.Label>Enter Water Intake ID</Form.Label>
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

            {waterIntake && (
                <Card className="mt-4">
                    <Card.Header as="h5">Water Intake Information</Card.Header>
                    <Card.Body>
                        <Card.Title>ID: {waterIntake.id}</Card.Title>
                        <Card.Text><strong>Information:</strong> {waterIntake.information}</Card.Text>
                    </Card.Body>
                </Card>
            )}
        </Container>
    );
};

export default GetWaterIntakeById;
