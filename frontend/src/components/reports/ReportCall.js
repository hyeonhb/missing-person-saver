// (제보방법) 담당 경찰관 전화 연결

import React from 'react';
import './OptionModal.css';

const ReportCall = () => {
  const handleCall = () => {
    window.location.href = 'tel:182';
  };

  return (
    <div className="option-modal">
      <div className="option-modal-content">
        <div className="button-container">
            <button onClick={handleCall}>182로 전화연결</button>
            </div>
      </div>
    </div>
  );
};

export default ReportCall;