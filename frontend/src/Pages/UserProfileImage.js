import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Image, Container, Alert } from 'react-bootstrap';

const UserProfileImage = () => {
  const [imageSrc, setImageSrc] = useState(null);
  const [error, setError] = useState('');
  const userId = localStorage.getItem('userId');
  const token = localStorage.getItem('token');

  useEffect(() => {
    const fetchImage = async () => {
      try {
        if (!userId || !token) {
          setError('User not authenticated. Please sign in.');
          return;
        }

        const response = await axios.get(
          `http://localhost:8080/users/${userId}/image`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
            responseType: 'blob', // Fetch data as a Blob
          }
        );

        // Create a URL for the blob image data
        const imageUrl = URL.createObjectURL(response.data);
        setImageSrc(imageUrl);
      } catch (error) {
        setError('Error fetching image');
        console.error('Error fetching image:', error);
      }
    };

    fetchImage();

    // Cleanup the URL object to avoid memory leaks
    return () => {
      if (imageSrc) {
        URL.revokeObjectURL(imageSrc);
      }
    };
  }, [userId, token]);

  return (
    <Container className="mt-5">
      <h2 className="mb-4">User Profile Image</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      {imageSrc ? (
        <Image src={imageSrc} alt="User Profile" fluid rounded />
      ) : (
        !error && <p>Loading image...</p>
      )}
    </Container>
  );
};

export default UserProfileImage;
