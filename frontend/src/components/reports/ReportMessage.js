// (제보방법) 메세지로 제보하기

import React, { useState } from 'react';
import './OptionModal.css';

const ReportMessage = ({ onClose, onSubmit }) => {
  const [message, setMessage] = useState('');

  const handleChange = (e) => setMessage(e.target.value);

  const handleSubmit = () => {
    onSubmit(message);
    onClose();
  };

  return (
    <div className="option-modal">
      <div className="option-modal-content">
        <textarea value={message} onChange={handleChange} />
        <div className="button-container">
          <button onClick={handleSubmit}>제보</button>
          <button onClick={onClose}>닫기</button>
          </div>
      </div>
    </div>
  );
};

export default ReportMessage;