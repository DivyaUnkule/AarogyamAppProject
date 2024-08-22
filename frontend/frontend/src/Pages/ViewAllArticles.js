import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Container, Table, Alert } from 'react-bootstrap';

const ViewAllArticles = () => {
  const [articles, setArticles] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchArticles();
  }, []);

  const fetchArticles = async () => {
    try {
      const response = await axios.get('http://localhost:8080/users/admin/getallarticles');
      setArticles(response.data);
    } catch (err) {
      setError('Error fetching articles. Please try again later.');
      console.error('Error fetching articles:', err);
    }
  };

  return (
    <Container className="mt-4">
      <h2>All Articles</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      {articles.length > 0 ? (
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Content</th>
            </tr>
          </thead>
          <tbody>
            {articles.map((article) => (
              <tr key={article.id}>
                <td>{article.id}</td>
                <td>{article.title}</td>
                <td>{article.content}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      ) : (
        <p>No articles found.</p>
      )}
    </Container>
  );
};

export default ViewAllArticles;