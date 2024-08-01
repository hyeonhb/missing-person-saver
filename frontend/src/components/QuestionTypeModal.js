import '../styles/Modal.css';
import { MdFaceUnlock } from "react-icons/md";
import { GiClothes } from "react-icons/gi";
import { useState } from 'react';

const QuestionTypeModal = ({ onClose, onSubmit }) => {
    const [questionType, setQuestionType] = useState(-1);

  const handleSubmit = () => {
    onSubmit(questionType)
    onClose()
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <p>질문 유형 선택</p>

        <div className='radio-container'>
            <div className={`button-wrapper ${questionType === 1 && 'activated'}`} onClick={() => setQuestionType(1)}>
                <MdFaceUnlock size={40} />
                <span>안면 정보</span>
            </div>
            <div className={`button-wrapper ${questionType === 2 && 'activated'}`} onClick={() => setQuestionType(2)}>
                <GiClothes size={40} />
                <span>의상 정보</span>
            </div>
        </div>
        <button className='question-submit-btn' disabled={questionType === -1} onClick={handleSubmit}>완료</button>
      </div>
    </div>
  );
};


export default QuestionTypeModal;