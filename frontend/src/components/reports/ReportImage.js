// (제보방법) 사진업로드로 제보하기

import React, { useState } from 'react';
import './OptionModal.css';

const ReportImage = ({ onSubmit }) => {
  const [image, setImage] = useState(null);

  const handleChange = (e) => {
    setImage(e.target.files[0]);
  };

  const handleSubmit = () => {
    if (image) {
      onSubmit(image);
    }
  };

  return (
    <div className="option-modal">
      <div className="option-modal-content">
        <input type="file" accept="image/*" onChange={handleChange} />
        <div className="button-container">
            <button disabled={image === null} onClick={handleSubmit}>제보</button>
        </div>
      </div>
    </div>
  );
};

export default ReportImage;