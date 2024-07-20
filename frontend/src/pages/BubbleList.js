import React from 'react';
import Bubble from '../components/Bubble';
import '../components/Bubble.css';

const BubbleList = ({ messages }) => {
  return (
    <>
      {messages.map((message, index) => (
        <div key={index} className={`message-container ${message.isUser ? 'user' : 'bot'}`}>
          <div className={`bubble-container ${message.isUser ? 'user-message' : 'bot-message'}`}>
            {!message.isUser && (
              <div className="profile">
                <img src={message.profileImg} alt="Profile" className="profile-img" />
              </div>
            )}
            <Bubble text={message.text} isUser={message.isUser} />
          </div>
        </div>
      ))}
    </>
  );
};

export default BubbleList;