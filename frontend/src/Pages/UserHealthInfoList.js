import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Container } from 'react-bootstrap';

const UserHealthInfoList = () => {
  const [userHealthInfos, setUserHealthInfos] = useState([]);

  useEffect(() => {
    // Fetch the user health info from the backend
    axios.get('http://localhost:8080/users/admin/getuserhealthinfos')
      .then(response => {
        setUserHealthInfos(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the data!', error);
      });
  }, []);

  return (
    <Container>
      <h2>User Health Information</h2>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>User Health Information</th>
            <th>Height</th>
            <th>Weight</th>
            <th>Age</th>
            <th>Created At</th>
            <th>Updated At</th>
            <th>User ID</th>
          </tr>
        </thead>
        <tbody>
          {userHealthInfos.map((info) => (
            <tr key={info.id}>
              <td>{info.id}</td>
              <td>{info.userHealthInformation}</td>
              <td>{info.height}</td>
              <td>{info.weight}</td>
              <td>{info.age}</td>
              <td>{info.createdAt}</td>
              <td>{info.updatedAt}</td>
              <td>{info.user ? info.user.id : 'N/A'}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default UserHealthInfoList;
