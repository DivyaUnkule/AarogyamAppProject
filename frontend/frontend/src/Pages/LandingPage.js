import React from 'react';
import { useNavigate } from 'react-router-dom';
import './LandingPage.css';

const LandingPage = () => {
  const navigate = useNavigate();

  return (
    <div className="landing-page">
      <header className="header">
        <div className="logo">Aarogyam</div>
        <nav>
          <button className="nav-button" onClick={() => navigate('/signin')}>Sign In</button>
          <button className="nav-button" onClick={() => navigate('/register')}>Register</button>
          <button className="nav-button" onClick={() => navigate('/about')}>About Us</button>
        </nav>
      </header>

      <main>
        <section className="hero">
        <h1><span class="highlight">Your Health Companion</span></h1>
          <p>Empowering you to take control of your health journey</p>
          <button className="cta-button" onClick={() => navigate('/register')}>Get Started</button>
        </section>

        <section className="features">
          <div className="feature">
            <img src="/path-to-icon1.png" alt="Health Tracking" />
            <h3>Health Tracking</h3>
            <p>Monitor your vital signs and health metrics easily</p>
          </div>
          <div className="feature">
            <img src="/path-to-icon2.png" alt="Personalized Advice" />
            <h3>Personalized Advice</h3>
            <p>Get tailored health recommendations based on your data</p>
          </div>
          <div className="feature">
            <img src="/path-to-icon3.png" alt="Connect with Experts" />
            <h3>Connect with Experts</h3>
            <p>Consult with healthcare professionals anytime, anywhere</p>
          </div>
        </section>

        <section className="cta">
          <h2>Start Your Health Journey Today</h2>
          <p>Join thousands of users who have improved their health with Aarogyam</p>
          <button className="cta-button" onClick={() => navigate('/register')}>Download Now</button>
        </section>
      </main>

      <footer>
        <p>&copy; 2024 Aarogyam. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default LandingPage;