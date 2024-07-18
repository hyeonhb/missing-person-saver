import React, { useState } from 'react';
import './Modal.css';

const Modal = ({ onClose, onSubmit }) => {
  const [name, setName] = useState('');
  const [contact, setContact] = useState('');

  const handleNameChange = (e) => setName(e.target.value);
  const handleContactChange = (e) => setContact(e.target.value);

  const handleSubmit = () => {
    if (name.trim() && contact.trim()) {
      onSubmit(name, contact);
      onClose();
    } else {
      alert("모든 필드를 입력해주세요.");
    }
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <h2>사용자 정보 입력</h2>
        <div>
          <label>이름:</label>
          <input type="text" value={name} onChange={handleNameChange} />
        </div>
        <div>
          <label>연락처:</label>
          <input type="text" value={contact} onChange={handleContactChange} />
        </div>
        <button onClick={handleSubmit}>제출</button>
      </div>
    </div>
  );
};

export default Modal;