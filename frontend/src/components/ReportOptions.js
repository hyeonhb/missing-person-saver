// 제보방법 선택


import React, { useState, useEffect } from 'react';
import './ReportOptions.css';
import ReportMessage from './reports/ReportMessage';
import ReportCall from './reports/ReportCall';
import ReportGPS from './reports/ReportGPS';
import ReportImage from './reports/ReportImage';

const ReportOptions = ({ onClose }) => {
  const [selectedOption, setSelectedOption] = useState('default');
  const [headerTitle, setHeaderTitle] = useState('')

  useEffect(() => {
    const TITLE_MAP = {
      default: '제보 방법을 선택해주세요',
      message: '제보할 내용을 입력해주세요',
      call: '담당 경찰관과 전화연결됩니다',
      gps: '위치 제보',
      image: '사진 제보',
    };
    const title = TITLE_MAP[selectedOption];
    setHeaderTitle(title);
  }, [selectedOption]);

  const handleOptionClick = (option) => {
    setSelectedOption(option);
  };

  const closeModal = () => {
    setSelectedOption('default');
  };

  return (
    <div className="report-options-modal">
      <div className="report-options-content">
        <div className="report-header">
          <h2>{headerTitle}</h2>
          <p className='report-closer' onClick={onClose}>✕</p>
        </div>

        {selectedOption === 'default' && 
          <div className="report-options-grid">
            <div className="report-option" onClick={() => handleOptionClick('message')}>메세지 제보하기</div>
            <div className="report-option" onClick={() => handleOptionClick('call')}>담당 경찰관 전화연결</div>
            <div className="report-option" onClick={() => handleOptionClick('gps')}>현재 위치 제보하기</div>
            <div className="report-option" onClick={() => handleOptionClick('image')}>사진 제보하기</div>
          </div>
        }

        {selectedOption === 'message' && <ReportMessage onClose={closeModal} onSubmit={(message) => alert(`제보 메시지: ${message}`)} />}
        {selectedOption === 'call' && <ReportCall onClose={closeModal} />}
        {selectedOption === 'gps' && <ReportGPS onClose={closeModal} />}
        {selectedOption === 'image' && <ReportImage onClose={closeModal} onSubmit={(image) => alert(`제보 이미지: ${image.name}`)} />}
      </div>
    </div>
  );
};

export default ReportOptions;