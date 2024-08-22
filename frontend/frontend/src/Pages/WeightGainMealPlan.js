import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Container, Card, ListGroup } from 'react-bootstrap';
import './WeightGainMealPlanPage.css';

const WeightGainMealPlanPage = () => {
    const [mealPlans, setMealPlans] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchMealPlans = async () => {
            try {
                const token = localStorage.getItem('jwtToken');
                const userId = localStorage.getItem('userId');
                const userRole = localStorage.getItem('userRole');

                console.log('Token:', token);
                console.log('User ID:', userId);
                console.log('User Role:', userRole);

                if (!userId) {
                    setError("User ID not found. Please log in again.");
                    return;
                }

                console.log('Fetching weight gain meal plans for user:', userId);

                const response = await axios.get(`http://localhost:8080/users/user/weightgainmealplan/${userId}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                    withCredentials: true
                });
                setMealPlans(response.data);
            } catch (err) {
                console.error("Error fetching weight gain meal plans:", err.response ? err.response.data : err.message);
                setError("Failed to load weight gain meal plans. Please try again later.");
            }
        };

        fetchMealPlans();
    }, []);

    return (
        <Container className="mt-4 meal-plan-container">
            <h1>Your Weight Gain Meal Plans</h1>
            {error && <p className="text-danger">{error}</p>}
            {mealPlans.length > 0 ? (
                mealPlans.map((mealPlan, index) => (
                    <Card key={index} className="mb-3">
                        <Card.Body>
                            <Card.Title>{mealPlan.name}</Card.Title>
                            <ListGroup variant="flush">
                                <ListGroup.Item>
                                    <h5>Description</h5>
                                    <p>{mealPlan.description}</p>
                                </ListGroup.Item>
                                <ListGroup.Item>
                                    <h5>Amount</h5>
                                    <p>{mealPlan.amount} Rs</p>
                                </ListGroup.Item>
                                <ListGroup.Item>
                                    <h5>Status</h5>
                                    <p>{mealPlan.status}</p>
                                </ListGroup.Item>
                            </ListGroup>
                        </Card.Body>
                    </Card>
                ))
            ) : (
                <p>No weight gain meal plans found.</p>
            )}
        </Container>
    );
};

export default WeightGainMealPlanPage;