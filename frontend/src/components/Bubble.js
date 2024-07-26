import React from 'react';
import './Bubble.css';

const Bubble = ({ text, isUser }) => {
  return (
    <div className={`bubble ${isUser ? 'user' : 'bot'}`}>
      <div className="bubble-content">{text}</div>
      <div className={`bubble-tail ${isUser ? 'user-tail' : 'bot-tail'}`}></div>
    </div>
  );
};

export default Bubble;