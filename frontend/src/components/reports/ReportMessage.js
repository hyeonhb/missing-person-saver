// (제보방법) 메세지로 제보하기

import React, { useState } from 'react';
import './OptionModal.css';

const ReportMessage = ({ onSubmit }) => {
  const [message, setMessage] = useState('');

  const handleChange = (e) => setMessage(e.target.value);

  const handleSubmit = () => {
    onSubmit(message);
  };

  return (
    <div className="option-modal">
      <div className="option-modal-content">
        <textarea value={message} onChange={handleChange} />
        <div className="button-container">
          <button onClick={handleSubmit}>제보</button>
          </div>
      </div>
    </div>
  );
};

export default ReportMessage;