// (제보방법) 사진업로드로 제보하기

import React, { useState } from 'react';
import './OptionModal.css';

const ReportImage = ({ onClose, onSubmit }) => {
  const [image, setImage] = useState(null);

  const handleChange = (e) => {
    setImage(e.target.files[0]);
  };

  const handleSubmit = () => {
    if (image) {
      onSubmit(image);
      onClose();
    }
  };

  return (
    <div className="option-modal">
      <div className="option-modal-content">
        <input type="file" accept="image/*" onChange={handleChange} />
        <div className="button-container">
            <button onClick={handleSubmit}>제보</button>
            <button onClick={onClose}>닫기</button>
        </div>
      </div>
    </div>
  );
};

export default ReportImage;