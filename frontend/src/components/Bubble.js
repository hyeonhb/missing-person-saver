import React from 'react';
import PropTypes from 'prop-types';
import '../components/Bubble.css';

const Bubble = ({ text, isUser }) => {
  return (
    <div className={`bubble ${isUser ? 'user' : 'bot'}`}>
      <p>{text}</p>
    </div>
  );
};

Bubble.propTypes = {
  text: PropTypes.string.isRequired,
  isUser: PropTypes.bool.isRequired,
};

export default Bubble;