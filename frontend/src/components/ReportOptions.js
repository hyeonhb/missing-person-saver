// 제보방법 선택


import React, { useState } from 'react';
import './ReportOptions.css';
import ReportMessage from './reports/ReportMessage';
import ReportCall from './reports/ReportCall';
import ReportGPS from './reports/ReportGPS';
import ReportImage from './reports/ReportImage';

const ReportOptions = ({ onClose }) => {
  const [selectedOption, setSelectedOption] = useState('default');

  const handleOptionClick = (option) => {
    setSelectedOption(option);
  };

  const closeModal = () => {
    setSelectedOption('default');
  };

  return (
    <div className="report-options-modal">
        {selectedOption === 'default' && 
          <div className="report-options-content">
          <div className="report-header">
            <h2>제보 방법을 선택해주세요</h2>
            <p className='report-closer' onClick={onClose}>✕</p>
          </div>

          <div className="report-options-grid">
            <div className="report-option" onClick={() => handleOptionClick('message')}>메세지 제보하기</div>
            <div className="report-option" onClick={() => handleOptionClick('call')}>담당 경찰관 전화연결</div>
            <div className="report-option" onClick={() => handleOptionClick('gps')}>현재 위치 제보하기</div>
            <div className="report-option" onClick={() => handleOptionClick('image')}>사진 제보하기</div>
          </div>
        </div>
        }

      {selectedOption === 'message' && <ReportMessage onClose={closeModal} onSubmit={(message) => alert(`제보 메시지: ${message}`)} />}
      {selectedOption === 'call' && <ReportCall onClose={closeModal} />}
      {selectedOption === 'gps' && <ReportGPS onClose={closeModal} />}
      {selectedOption === 'image' && <ReportImage onClose={closeModal} onSubmit={(image) => alert(`제보 이미지: ${image.name}`)} />}
    </div>
  );
};

export default ReportOptions;