import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';

const DeleteArticle = () => {
  const [articleId, setArticleId] = useState('');
  const [message, setMessage] = useState('');
  const [isSuccess, setIsSuccess] = useState(false);

  const handleChange = (e) => {
    setArticleId(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setIsSuccess(false);

    if (!articleId) {
      setMessage('Please enter an article ID.');
      return;
    }

    try {
      await axios.delete(`http://localhost:8080/users/admin/deletearticle/${articleId}`);
      setMessage('Article deleted successfully!');
      setIsSuccess(true);
      setArticleId(''); // Clear the input after successful deletion
    } catch (error) {
      setMessage('Error deleting article. Please try again.');
      console.error('Error deleting article:', error);
    }
  };

  return (
    <Container className="mt-4">
      <h2>Delete Article</h2>
      {message && (
        <Alert variant={isSuccess ? 'success' : 'danger'}>
          {message}
        </Alert>
      )}
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>Article ID</Form.Label>
          <Form.Control
            type="number"
            value={articleId}
            onChange={handleChange}
            required
            min="1"
          />
        </Form.Group>
        <Button variant="danger" type="submit">
          Delete Article
        </Button>
      </Form>
    </Container>
  );
};

export default DeleteArticle;