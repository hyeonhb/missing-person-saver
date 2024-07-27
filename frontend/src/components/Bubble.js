import React from 'react';
import '../styles/Bubble.css';

const Bubble = ({ text, isUser }) => {
  return (
    <div className={`bubble ${isUser ? 'user' : 'bot'}`}>
      <div className="bubble-content">{text}</div>
    </div>
  );
};

export default Bubble;