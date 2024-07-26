import React, { useState } from 'react';
import './Modal.css';

const Modal = ({ onClose, onSubmit }) => {
  const [name, setName] = useState('');
  const [contact, setContact] = useState('');

  const handleNameChange = (e) => setName(e.target.value);
  const handleContactChange = (e) => {
    const value = e.target.value;
    // 연락처 형식을 000-0000-0000으로 제한
    const formattedValue = formatContact(value);
    setContact(formattedValue);
  };

  const formatContact = (value) => {
    // 숫자와 "-"만 남기기
    const cleanedValue = value.replace(/[^\d-]/g, '');

    // 길이를 13자로 제한
    if (cleanedValue.length > 13) {
      return cleanedValue.slice(0, 13);
    }

    // 000-0000-0000 형식 맞추기
    const parts = cleanedValue.split('-').join('').match(/(\d{0,3})(\d{0,4})(\d{0,4})/);
    const formatted = !parts[2]
      ? parts[1]
      : `${parts[1]}-${parts[2]}${parts[3] ? `-${parts[3]}` : ''}`;
    return formatted;
  };

  const handleSubmit = () => {
    if (name.trim() && contact.trim()) {
      onSubmit(name, contact);
      window.localStorage.setItem('name', name);
      window.localStorage.setItem('contact', contact);
      onClose();
    } else {
      alert("이름과 연락처를 모두 입력해주세요.");
    }
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <h2>제보자 정보 입력</h2>
        <div>
          <label>이름</label>
          <input 
            type="text" 
            value={name} 
            onChange={handleNameChange} 
            placeholder="홍길동" // 기대값 형식을 미리 보여주기 
          />
        </div>
        <div>
          <label>연락처</label>
          <input type="text" value={contact} onChange={handleContactChange} placeholder="000-0000-0000" />
        </div>
        <button onClick={handleSubmit}>완료</button>
      </div>
    </div>
  );
};


export default Modal;