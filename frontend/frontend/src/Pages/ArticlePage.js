import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ArticlePage.css';



const ArticlePage = () => {
    const [articles, setArticles] = useState([]);
  
    useEffect(() => {
      const fetchArticles = async () => {
        try {
          const response = await axios.get('http://localhost:8080/users/user/getALLArticles');
          setArticles(response.data);
        } catch (error) {
          console.error('Error fetching articles:', error);
        }
      };
  
      fetchArticles();
    }, []);
  
    if (articles.length === 0) return <div>Loading...</div>;
  
    return (
      <div className="article-page">
        {articles.map(article => (
          <div key={article.id}>
            <h1>{article.title}</h1>
            <p>{article.content}</p>
          </div>
        ))}
      </div>
    );
  };
  
 

export default ArticlePage;