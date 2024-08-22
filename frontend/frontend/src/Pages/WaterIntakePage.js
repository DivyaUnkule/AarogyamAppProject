import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Container, Table } from 'react-bootstrap';
import './WaterIntakePage.css';

const WaterIntakePage = () => {
  const [waterIntakes, setWaterIntakes] = useState([]);

  useEffect(() => {
    fetchWaterIntakes();
  }, []);

  const fetchWaterIntakes = async () => {
    try {
      const response = await axios.get('http://localhost:8080/users/user/getwaterintakes');
      setWaterIntakes(response.data);
    } catch (error) {
      console.error("Error fetching water intakes:", error);
    }
  };

  return (
    <Container className="water-intake-page">
      <h1>Your Water Intake</h1>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Information</th>
          </tr>
        </thead>
        <tbody>
          {waterIntakes.map((intake) => (
            <tr key={intake.id}>
              <td>{intake.id}</td>
              <td>{intake.information}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default WaterIntakePage;