import React, { useState, useRef, useEffect } from 'react';
import BubbleList from './BubbleList'; 
import '../styles/Bubble.css';
import Modal from '../components/Modal';

const Chat = () => {
  const [showModal, setShowModal] = useState(false);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');

  const messagesEndRef = useRef(null);
  const inputRef = useRef(null);

  useEffect(() => {
    openModal(window.localStorage);
  } , []);

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  function openModal(profile) {
    // 기존 profile 정보가 있는지 체크
    const hasProfile = Object.keys(profile).length !== 0;

    if (hasProfile) initChat(); // 이미 있으면 바로 채팅 시작
    else setShowModal(true); // 없을 때만 표시
  }

  const initChat = () => {
    setMessages([]);
    setNewMessage('');
  };

  const handleInputChange = (e) => {
    setNewMessage(e.target.value);
  };

  const updateNewMsg = () => {
    const updatedMsg = {
      text: newMessage,
      isUser: true,
    };

    setNewMessage('');
    setMessages([...messages, updatedMsg]);

    focusInput();
  }

  const closeModal = () => {
    setShowModal(false);
  };

  const handleModalSubmit = (name, contact) => {
    //
  };

  const scrollToBottom = () => {
    if (messagesEndRef.current) {
      messagesEndRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  const focusInput = () => {
    if (inputRef.current) inputRef.current.focus();
  }

  return (
    <div className="chat-container">
      {showModal && <Modal onClose={closeModal} onSubmit={handleModalSubmit} />}
      <section className="chat-messages">
        <BubbleList messages={messages} />
        <div ref={messagesEndRef} />
      </section>
      <div className="chat-input-container">
          <input
            ref={inputRef}
            type="text"
            className="chat-input"
            placeholder="메시지를 입력하세요."
            value={newMessage}
            onChange={handleInputChange}
            onKeyPress={(e) => {
              if (e.key === 'Enter') updateNewMsg();
            }}
          />
          <button className="send-button" onClick={updateNewMsg}>
            전송
          </button>
        </div>
    </div>
  );
};

export default Chat;