import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Alert } from 'react-bootstrap';

const InsertArticle = () => {
  const [article, setArticle] = useState({
    title: '',
    content: ''
  });
  const [message, setMessage] = useState('');
  const [isSuccess, setIsSuccess] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setArticle(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setIsSuccess(false);

    try {
      const response = await axios.post('http://localhost:8080/users/admin/insertarticles', article);
      setMessage('Article inserted successfully!');
      setIsSuccess(true);
      console.log('Inserted article:', response.data);
      // Clear the form after successful insertion
      setArticle({ title: '', content: '' });
    } catch (error) {
      setMessage('Error inserting article. Please try again.');
      console.error('Error inserting article:', error);
    }
  };

  return (
    <Container className="mt-4">
      <h2>Insert New Article</h2>
      {message && (
        <Alert variant={isSuccess ? 'success' : 'danger'}>
          {message}
        </Alert>
      )}
      <Form onSubmit={handleSubmit}>
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
          Insert Article
        </Button>
      </Form>
    </Container>
  );
};

export default InsertArticle;