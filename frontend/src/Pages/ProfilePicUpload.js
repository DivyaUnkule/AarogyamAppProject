import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Alert, Container } from 'react-bootstrap';

const ProfilePicUpload = () => {
  const [imageFile, setImageFile] = useState(null);
  const [uploadSuccess, setUploadSuccess] = useState(false);
  const [uploadError, setUploadError] = useState('');

  const handleFileChange = (e) => {
    setImageFile(e.target.files[0]);
  };

  const handleUpload = async (e) => {
    e.preventDefault();
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if (!userId || !token) {
      setUploadError('User not authenticated. Please sign in.');
      return;
    }

    const formData = new FormData();
    formData.append('imageFile', imageFile);

    try {
      const response = await axios.post(
        `http://localhost:8080/users/${userId}/image_upload`,
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setUploadSuccess(true);
      setUploadError('');
      console.log('Image uploaded successfully:', response.data);
    } catch (error) {
      setUploadError('Error uploading image');
      setUploadSuccess(false);
      console.error('Error uploading image:', error);
    }
  };

  return (
    <Container className="mt-5">
      <h2 className="mb-4">Upload Profile Picture</h2>
      <Form onSubmit={handleUpload}>
        <Form.Group className="mb-3">
          <Form.Label>Select an image</Form.Label>
          <Form.Control
            type="file"
            onChange={handleFileChange}
            accept="image/*"
            required
          />
        </Form.Group>

        {uploadSuccess && <Alert variant="success">Image uploaded successfully!</Alert>}
        {uploadError && <Alert variant="danger">{uploadError}</Alert>}

        <Button variant="primary" type="submit">
          Upload
        </Button>
      </Form>
    </Container>
  );
};

export default ProfilePicUpload;
