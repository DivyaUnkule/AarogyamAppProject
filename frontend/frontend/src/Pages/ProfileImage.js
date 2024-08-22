import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Image, Alert } from 'react-bootstrap';

const ProfileImage = ({ userId }) => {
  const [imageUrl, setImageUrl] = useState(null);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchImage = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get(
          `http://localhost:8080/users/user/${userId}/image`,
          {
            responseType: 'blob',
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        const imageObjectUrl = URL.createObjectURL(response.data);
        setImageUrl(imageObjectUrl);
      } catch (error) {
        console.error('Error fetching image:', error);
        setError('Failed to load profile image');
      }
    };

    fetchImage();
  }, [userId]);

  return (
    <div>
      {imageUrl ? (
        <Image src={imageUrl} alt="Profile" roundedCircle style={{ width: '200px', height: '200px' }} />
      ) : error ? (
        <Alert variant="danger">{error}</Alert>
      ) : (
        <p>Loading image...</p>
      )}
    </div>
  );
};

export default ProfileImage;