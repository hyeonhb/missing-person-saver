import React from 'react';
import '../styles/Bubble.css';

const Bubble = ({ text }) => {
  return (
    <div className={`bubble`}>
      <div className="bubble-content">{text}</div>
    </div>
  );
};

export default Bubble;