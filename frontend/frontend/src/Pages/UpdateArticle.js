import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';

const UpdateArticle = () => {
  const [article, setArticle] = useState({
    id: '',
    title: '',
    content: ''
  });
  const [message, setMessage] = useState('');
  const [isSuccess, setIsSuccess] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setArticle(prevState => ({
      ...prevState,
      [name]: name === 'id' ? parseInt(value) : value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setIsSuccess(false);

    try {
      const response = await axios.put('http://localhost:8080/users/admin/updatearticles', article);
      setMessage('Article updated successfully!');
      setIsSuccess(true);
      console.log('Updated article:', response.data);
    } catch (error) {
      setMessage('Error updating article. Please try again.');
      console.error('Error updating article:', error);
    }
  };

  return (
    <Container className="mt-4">
      <h2>Update Article</h2>
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
            name="id"
            value={article.id}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Title</Form.Label>
          <Form.Control
            type="text"
            name="title"
            value={article.title}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Content</Form.Label>
          <Form.Control
            as="textarea"
            rows={5}
            name="content"
            value={article.content}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          Update Article
        </Button>
      </Form>
    </Container>
  );
};

export default UpdateArticle;