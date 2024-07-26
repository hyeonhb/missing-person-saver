import React from 'react';
import '../styles/Bubble.css';

const BubbleList = ({ messages, onOptionSelect }) => {
  return (
    <div className="bubble-list">
      {messages.map((message, index) => (
        <div key={index} className={`message-container ${message.isUser ? 'user' : 'bot'}`}>
          <div className={`bubble-container ${message.isUser ? 'user-message' : 'bot-message'}`}>
            {!message.isUser && (
              <div className="profile">
                <img src={message.profileImg} alt="Profile" className="profile-img" />
              </div>
            )}
            <div className="bubble">{message.text}</div>
          </div>
        </div>
      ))}
      {messages.length > 0 && !messages[messages.length - 1].isUser && messages[messages.length - 1].options && (
        <div className="options-container">
          {messages[messages.length - 1].options.map((option, index) => (
            <button
              key={index}
              className="option-button"
              onClick={() => onOptionSelect(option)}
            >
              {option}
            </button>
          ))}
        </div>
      )}
    </div>
  );
};

export default BubbleList;