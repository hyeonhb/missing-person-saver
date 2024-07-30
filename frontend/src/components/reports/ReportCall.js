// (제보방법) 담당 경찰관 전화 연결

import React from 'react';
import './OptionModal.css';

const ReportCall = ({ onClose }) => {
  const handleCall = () => {
    window.location.href = 'tel:182';
  };

  return (
    <div className="option-modal">
      <div className="option-modal-content">
        <h2>담당 경찰관과 전화연결됩니다.</h2>
        <div className="button-container">
            <button onClick={handleCall}>182로 전화연결</button>
            <button onClick={onClose}>닫기</button>
            </div>
      </div>
    </div>
  );
};

export default ReportCall;