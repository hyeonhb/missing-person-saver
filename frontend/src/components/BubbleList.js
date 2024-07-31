import React from 'react';
import Bubble from '../components/Bubble';
import '../styles/Bubble.css';

const BubbleList = ({ messages }) => {
  return (
    <div className="bubble-list">
      {messages.map((message, index) => (
        <div key={index} className={`message-container ${message.isUser ? 'user' : 'bot'}`}>
          {!message.isUser && (
              <div className="profile">
                <img src="/chatbot_icon.png" alt="Profile" className="profile-img" />
              </div>
            )}
            <Bubble text={message.text} />
        </div>
      ))}
    </div>
  );
};

export default BubbleList;