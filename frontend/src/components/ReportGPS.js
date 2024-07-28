// (제보방법) 현재위치로 제보하기

import React from 'react';
import './OptionModal.css';

const ReportGPS = ({ onClose }) => {
  return (
    <div className="option-modal">
      <div className="option-modal-content">
        <h2>위치 제보</h2>
        <div className="map-container">
          {/* 여기서 지도 API를 통해 지도를 불러옵니다. 예시로는 빈 div로 설정했습니다. */}
          <div className="map-placeholder">지도 표시 영역</div>
        </div>
        <button onClick={onClose}>닫기</button>
      </div>
    </div>
  );
};

export default ReportGPS;